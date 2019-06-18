package Controller;


import Applicatie.Applicatie;
import Managers.SceneManager;
import Model.LobbyModel;
import Observer.LobbyObserver;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LobbyController {

	LobbyModel lobbymodel = new LobbyModel();

	public LobbyController(){
		SceneManager.getInstance().createLobbyView(this);
	}

	public void joinLobby(String Name){
		Applicatie app = SceneManager.getInstance().getApp();
		if(app.getFirebaseService().joinLobby(Name, app.getAccountCon().getAccountName())){
			new InLobbyController(Name);
		}
	}

	public String getLobbyNaam(){
		return lobbymodel.getLobbyNaam();
	}

	public void register(LobbyObserver ob) {
		lobbymodel.register(ob);
	}

	public void hostLobby(String lobbyName) {
		lobbymodel.hostLobby(lobbyName);
	}

	public void lobbyEdit(){
		new LobbySettingsController();
	}

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


















	public void exitLobby(int decreaseLobbySize) {   
		lobbymodel.exitLobby(decreaseLobbySize);
	}

	public void getLobbyAmount() {
		lobbymodel.getLobbyAmount();
	}


	public void spelerToevoegen() {            // berekening om speler toe te voegen
		lobbymodel.spelerToevoegen();
	}

	public void joinGame() {		// Joinen van een game
		lobbymodel.joinGame();
	}

	public int getLobbySizeCounter() {
		return lobbymodel.getLobbySizeCounter();
	}



}
