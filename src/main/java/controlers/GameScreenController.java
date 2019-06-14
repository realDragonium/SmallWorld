package controlers;

import managers.SceneManager;

public class GameScreenController {
	
	Map2DController mapController;
	PlayerListController playerListController;
	DiceController diceController;
	PlayerController playerController;
	
	public GameScreenController() {
		SceneManager.createGameScreenView(this);
		createGameParts();
		
	}
	
	public void createGameParts() {
		diceController = new DiceController();
		playerListController = new PlayerListController();
		mapController = new Map2DController();
		playerController = new PlayerController();
	}
}
