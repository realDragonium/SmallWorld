package Controller;

import Model.ShopModel;
import Objects.*;
import Managers.SceneManager;
import Observer.ShopObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopController {

    GameController gameCon;
    ShopModel model = new ShopModel();
    private List<RaceController> races = new ArrayList<>();
    private List<Power> powers = new ArrayList<>();

     ShopController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadShop(this);

        createShopItems();
        for(int i = 0; i < 6; i++){
            makeNewCombination();
        }
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
        races.add(new RaceController(new RattenKracht(), "rats", 12));
        races.add(new RaceController(new WizzardsKracht(), "wizzards", 12));
        races.add(new RaceController(new DwarvesKracht(), "dwarves", 12));
        races.add(new RaceController(new TritansKracht(), "tritans", 12));
        races.add(new RaceController(new HumanKracht(), "humans", 12));

        powers.add(new AlchemistPower());
        powers.add(new WelthPower());
        powers.add(new AlchemistPower());
        powers.add(new WelthPower());
        powers.add(new AlchemistPower());
    }

    private void makeNewCombination(){
        RaceController race = getRandomRace();
        Power power = getRandomPower();
        if(race != null && power != null) {
            CombinationController combination = new CombinationController(race, power);
            race.setCombiCon(combination);
            model.addShopItem(combination);
        }
    }

    private RaceController getRandomRace(){
        if(races.size() != 0) {
            return races.remove(new Random().nextInt(races.size()));
        }
        return null;
    }

    private Power getRandomPower(){
        if(powers.size() != 0) {
            return powers.remove(new Random().nextInt(powers.size()));
        }
        return null;
    }

}
