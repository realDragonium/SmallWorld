package controlers;

import models.GameModel;
import Observer.ModelViewObserver;

public class GameController {
	private controlers.ApplicatieController appCon = controlers.ApplicatieController.getInstance();
	static GameController gameCon = new GameController();
	private GameModel gameModel = new GameModel();
	
	public static GameController getInstance() {
		return gameCon;
	}
	    
	public void register(ModelViewObserver mvo) {
		gameModel.register(mvo);
	}
}
