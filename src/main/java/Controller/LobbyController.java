package Controller;

import Applicatie.Applicatie;
import Managers.SceneManager;
import Model.LobbyModel;
import Observer.LobbyObserver;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**  This Controller-class is part of the MVC design pattern and shows the lobby screen (available lobbies).
 * @author: Lars Puente Blom
 * @version: June 2019
 *
 */

public class LobbyController {
	LobbyModel lobbymodel = new LobbyModel();



	public LobbyController(){
		SceneManager.getInstance().createLobbyView(this);
	}


	public void joinLobby(String Name){
		Applicatie app = SceneManager.getInstance().getApp();
		int id = app.getFirebaseService().joinLobby(Name, app.getAccountCon().getAccountName());
		if(id>0){
			new InLobbyController(Name, id);
		}
	}

	public void register(LobbyObserver ob) {
		lobbymodel.register(ob);
	}


	// Creates a new LobbySettingsController which gets its corresponding view in the SceneManager, by giving itself along
	public void lobbyEdit(){
		new LobbySettingsController();
	}


	// Tries to get a list of available lobbies.
	public List<String> getFirebaseLobbyNamen(){
		try {
			return SceneManager.getInstance().getApp().getFirebaseService().getActiveLobbies();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}



//	public String getLobbyNaam(){
//		return lobbymodel.getLobbyNaam();
//	}
//
//	public void hostLobby(String lobbyName) {
//		lobbymodel.hostLobby(lobbyName);
//	}
//
//	public void exitLobby(int decreaseLobbySize) {
//		lobbymodel.exitLobby(decreaseLobbySize);
//	}
//
//	public void getLobbyAmount() {
//		lobbymodel.getLobbyAmount();
//	}
//
//
//	public void spelerToevoegen() {            // berekening om speler toe te voegen
//		lobbymodel.spelerToevoegen();
//	}
//
//	public void joinGame() {		// Joinen van een game
//		lobbymodel.joinGame();
//	}
//
//	public int getLobbySizeCounter() {
//		return lobbymodel.getLobbySizeCounter();
//	}
//


}
