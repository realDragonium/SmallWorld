package DiceMVC;

public class DiceController {

	DiceModel diceModel = new DiceModel();
	
	public void ClickedDice() {
		RollDice();
	}
	
	private void RollDice() {
		int uitkomst = (int) Math.floor(Math.random() * 6);

		diceModel.changeSide(uitkomst);
	}

	public void registreer(Observer ob){
		diceModel.register(ob);
	}






	
}
