package Controller;


import Managers.SceneManager;
import Model.LobbyModel;
import Observer.LobbyObserver;

public class LobbyController {
	LobbyModel lobbymodel = new LobbyModel();
	//LobbySettingsController con = new LobbySettingsController();

	public LobbyController(){
		SceneManager.getInstance().createLobbyView(this);
	}

	public void register(LobbyObserver ob) {
		lobbymodel.register(ob);
	}

	public void hostLobby(String lobbyName) {
		lobbymodel.hostLobby(lobbyName);
	}




//	public void startInLobbyScreen(){
//		new InLobbyController();
//	}

//	public void startInLobbyScreen(String lobbyNaam){
//		new InLobbyController(lobbyNaam);
//	}

//
//
//	public void setLobbyName(String lobbyName){
//		con.setLobbyName(lobbyName);
//	}
//
//	public void getLobbyName(String lobbyName){
//		con.setLobbyName(lobbyName);
//	}





	public void lobbyEdit(){
		new LobbySettingsController();
	}


//	public void lobbyEdit(String lobbyNaam){
//		new LobbySettingsController(lobbyNaam);
//	}

	public void exitLobby(int decreaseLobbySize) {   
		lobbymodel.exitLobby(decreaseLobbySize);
	}

	public void getLobbyAmount() {
		lobbymodel.getLobbyAmount();
	}




	public void lobbyNaamOpvragen() {
		lobbymodel.lobbyNaamOpvragen();
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
