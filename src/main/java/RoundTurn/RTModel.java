package main.java.RoundTurn;

import java.util.ArrayList;
import java.util.List;

public class RTModel implements RTObservable {
	private List<String> rounds = new ArrayList<>();
	private List<String> players = new ArrayList<>();
	private int indexcurrentplayers = 0;
	private int indexcurrentrounds = 0;
	private int realrounds = 0;
	private String currentRound = "Round 1";
	private String currentPlayer;
	List<RTObserver> observers = new ArrayList<>();

	public RTModel() {
		players.add("Player 1");
		players.add("Player 2");
		players.add("Player 3");
		players.add("Player 4");
		
		rounds.add("Round 1");
		rounds.add("Round 2");
		rounds.add("Round 3");
		rounds.add("Round 4");
		rounds.add("Round 5");
		rounds.add("Round 6");
		rounds.add("Round 7");
		rounds.add("Round 8");
		rounds.add("Round 9");
		rounds.add("END GAME");
	}
	
	public void StartTurnNextPlayer() {
		//als er geklikt wordt volgende 
		indexcurrentplayers++;
		//max 4 spelers daarna weer speler 1
		if(indexcurrentplayers > 3 ) indexcurrentplayers =0;
		//van spelers get het nummer daarvan de string
		currentPlayer = players.get(indexcurrentplayers);
		notifyAllObs();
	}
	
	public void UpdateRounds() {
		if(indexcurrentplayers == 0) {
			realrounds++;
			currentRound = rounds.get(realrounds);		
		}
		
		notifyAllObs();
	}

	@Override
	public void notifyAllObs() {
		for(RTObserver ob : observers) {
			ob.update(this);
		}
		
	}

	@Override
	public void register(RTObserver ob) {
		observers.add(ob);
		
	}

	@Override
	public String getWhichTurn() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}

	@Override
	public String getRounds() {
		// TODO Auto-generated method stub
		return currentRound;
	}



}
