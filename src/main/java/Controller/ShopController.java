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
        System.out.println(gameCon.getCurrentPlayer().getId() + " is buying");
        if(shopItems.get(item) != null){
            gameCon.getCurrentPlayer().buyFromShop(shopItems.get(item), item);
        }

    }

    private void createShopItems(){
        shopItems.add(createRattenRace());
    }

    private CombinationController createRattenRace(){
        CombinationController ratten =  new CombinationController(new RaceController(new RattenKracht()), new PowerController());
        ratten.getRace().setCombiCon(ratten);
        ratten.getPower().setCombiCon(ratten);
        return ratten;
    }
}
