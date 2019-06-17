package DiceMVC;

public class DiceController {

	DiceModel diceModel = new DiceModel();

	private static DiceController diceController;
	public void ClickedDice() {
		RollDice();

	}
	
	private void RollDice() {
		int uitkomst = (int) (Math.floor(Math.random() * 6));
		diceModel.changeSide(uitkomst);
		//uitkomst += 1;
		diceModel.giveValue(uitkomst);

	}

	public void registreer(Observer ob){
		diceModel.register(ob);
	}

	public static DiceController getInstance(){
		if (diceController== null){
			diceController = new DiceController();
		}
		return diceController;
	}






	
}
