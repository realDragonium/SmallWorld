package Controller;

import Objects.HumanKracht;
import Objects.RattenKracht;
import Managers.SceneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class ShopController {

    GameController gameCon;
    private List<CombinationController> shopItems = new ArrayList<>();

    public ShopController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadShop(this);
        createShopItems();
    }

    public void buyingItem(int item){
        System.out.println(gameCon.getCurrentPlayer().getId() + " is buying");
        gameCon.getCurrentPlayer().buyFromShop(shopItems.get(item), item);
        shopItems.remove(item);
        
    }

    private void createShopItems(){
        shopItems.add(createHumanRace());
        shopItems.add(createRattenRace());
    }

    private CombinationController createRattenRace(){
        CombinationController ratten =  new CombinationController(new RaceController(new RattenKracht(), "rats", 12), new PowerController());
        ratten.getRace().setCombiCon(ratten);
        ratten.getPower().setCombiCon(ratten);
        return ratten;
    }

    private CombinationController createHumanRace(){
        CombinationController human =  new CombinationController(new RaceController(new HumanKracht(), "humans", 8), new PowerController());
        human.getRace().setCombiCon(human);
        human.getPower().setCombiCon(human);
        return human;
    }
}
