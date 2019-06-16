package Controller;

import Objects.RattenKracht;
import Managers.SceneManager;

import java.util.Stack;

public class ShopController {

    GameController gameCon;
    private Stack<CombinationController> shopItems = new Stack<>();

    public ShopController(GameController gameCon){
        this.gameCon = gameCon;
        createShopItems();
        SceneManager.getInstance().loadShop(this);
    }

    public void buyingItem(int item){
        System.out.println(gameCon.getPlayer().getId() + " is buying");
        if(shopItems.get(item) != null){
            gameCon.getPlayer().buyFromShop(shopItems.get(item), item);
        }

    }

    private void createShopItems(){
        shopItems.add(createRattenRace());
    }

    private CombinationController createRattenRace(){
        return new CombinationController(new RaceController(new RattenKracht()), new PowerController());
    }
}
