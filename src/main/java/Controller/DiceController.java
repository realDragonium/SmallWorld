package Controller;


import Managers.SceneManager;
import Model.DiceModel;
import Observer.DiceObserver;

public class DiceController {

    DiceModel diceModel = new DiceModel();


    public void ClickedDice() {
        RollDice();

    }


    public DiceController() {
        SceneManager.getInstance().loadDice(this);
    }

    private void RollDice() {
        int uitkomst = (int) (Math.floor(Math.random() * 6));
        diceModel.changeSide(uitkomst);
        //uitkomst += 1;
        diceModel.giveValue(uitkomst);

    }

    public void registreer(DiceObserver ob) {
        diceModel.register(ob);
    }

}


