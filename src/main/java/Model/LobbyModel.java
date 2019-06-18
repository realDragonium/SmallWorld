package Model;

import Observer.LobbyObserver;

import java.util.ArrayList;
import java.util.List;

public class LobbyModel implements LobbyObservable {

	private List<LobbyObserver> observers = new ArrayList<>();
	private String lobbySize[];  // max 4 spelers
	private int lobbySizeCounter = 0;
	private int lobbyAmount = 0;
	
	private String[] spelers = {"Speler 1", "Speler 2", "Speler 3", "Speler 4"};

	private boolean mode = true;



	public void setLobbyNaam(String lobbyNaam) {
		this.lobbyNaam = lobbyNaam;
	}

	private String lobbyNaam;
	
	@Override
	public void notifyAllObservers() {
		for(LobbyObserver obs : observers) {
			obs.update(mode);
		}	
	}

	public void setLobbyName(String lobbyName){
		this.lobbyNaam = lobbyName;
	}

	public void getLobbyNaam() {

	}




	public int getLobbyAmount() {
		return lobbyAmount;
	}

	public void hostLobby(String lobNaam) {
		mode = !mode;
		lobbySize = new String[4];           // Lege array die 4 groot is.
		lobbyAmount++;  // aantal lobbies = +1
		
		lobbyNaam = lobNaam;
		System.out.println(lobbyNaam);
		spelerToevoegen();
		notifyAllObservers();
	}

	public void exitLobby(int decreaseLobbySize) {
		mode = !mode;
		
		lobbyAmount = decreaseLobbySize;
		lobbyAmount--;
		lobbySizeCounter--;           
		System.out.println("Lobby is verwijderd   ");

		notifyAllObservers();
	}

	public void joinGame() {
		spelerToevoegen();
	}


	public void spelerToevoegen() {
		try {
			if(lobbySizeCounter < 4) {
				lobbySize[lobbySizeCounter] = spelers[lobbySizeCounter];
				System.out.println(lobbySize[lobbySizeCounter]);
				lobbySizeCounter++;
			}
			
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Lobby zit vol");
			}
	}
	
	
	public int getLobbySizeCounter() {
		return lobbySizeCounter;
	}
	
	public String lobbyNaamOpvragen() {
		return lobbyNaam;
	}
		


	@Override
	public void register(LobbyObserver ob) {
		observers.add(ob);
		
	}

	@Override
	public void unregister(LobbyObserver ob) {
		observers.remove(ob);
	}



	
	
	
	
	
	
	
}
