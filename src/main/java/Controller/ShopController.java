package Controller;

import Model.ShopModel;
import Objects.HumanKracht;
import Objects.RattenKracht;
import Managers.SceneManager;
import Observer.ShopObserver;

public class ShopController {

    GameController gameCon;
    ShopModel model = new ShopModel();

    public ShopController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadShop(this);
        createShopItems();
    }

    public void buyingItem(int item){
        System.out.println(gameCon.getCurrentPlayer().getId() + " is buying");
        if(model.getShopItems().size() > item) {
            gameCon.getCurrentPlayer().buyFromShop(model.getShopItems().get(item), item);
            model.removeItem(item);
        }
    }

    public void registerObserver(ShopObserver obs){
        model.register(obs);
    }

    private void createShopItems(){
        model.addShopItem(createHumanRace());
        model.addShopItem(createRattenRace());
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
