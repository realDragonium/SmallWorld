package controlers;

import managers.SceneManager;
import models.DiceModel;
import observers.DiceObserver;

public class DiceController {

	DiceModel diceModel = new DiceModel();
	
	public DiceController() {
		SceneManager.createDiceView(this);
	}

	public void ClickedDice() {
		RollDice();
	}

	private void RollDice() {
		int uitkomst = (int) Math.floor(Math.random() * 6) + 1;
		
		diceModel.changeSide(uitkomst);
	}

	public void registreer(DiceObserver ob){
		diceModel.register(ob);
	}
}