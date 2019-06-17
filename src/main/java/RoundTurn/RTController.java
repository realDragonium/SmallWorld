package main.java.RoundTurn;

import RoundTurn.RTController;
import RoundTurn.RTModel;
import RoundTurn.RTObserver;

public class RTController {

	RTModel turnModel = new RTModel();
	
	
	public void StartTurnNextPlayer() {
		turnModel.StartTurnNextPlayer();
		turnModel.UpdateRounds();
	}
	

	public void ClickedEndTurn() {
		StartTurnNextPlayer();
	
	}


	public void registerObserver(RTObserver ob) {
		turnModel.register(ob);
		
	}

}