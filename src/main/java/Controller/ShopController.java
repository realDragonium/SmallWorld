package Controller;

import Objects.RattenKracht;
import Managers.SceneManager;

import java.util.Stack;
import java.util.stream.IntStream;

public class ShopController {

    GameController gameCon;
    private Stack<CombinationController> shopItems = new Stack<>();

    public ShopController(GameController gameCon){
        this.gameCon = gameCon;
        IntStream.range(0, 6).forEach(e-> createShopItems());
        SceneManager.getInstance().loadShop(this);
    }

    public void buyingItem(int item){
        System.out.println(gameCon.getCurrentPlayer().getId() + " is buying");
        gameCon.getCurrentPlayer().buyFromShop(shopItems.get(item), item);
        shopItems.remove(item);
        createShopItems();
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
