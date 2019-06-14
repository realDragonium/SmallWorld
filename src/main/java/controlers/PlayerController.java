package controlers;

import java.util.ArrayList;
import java.util.List;

import managers.SceneManager;
import models.PlayerModel;

public class PlayerController {
	List<PlayerModel> players = new ArrayList<>();
	
	public PlayerController() {
		createPlayer();
	}
	
	public void createPlayer() {
		String playerId = "player_" + (players.size() + 1);
		
		SceneManager.createPlayerViews(this, playerId);

		players.add(new PlayerModel(playerId));
	}

}
