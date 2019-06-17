package Model;

import Controller.AreaController;
import Objects.RaceFiche;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class RaceModel {

	private int beginCountFiches;
	
	private Stack<RaceFiche> availableFiches = new Stack<>();
	private int fichesAantal = 15;
	private List<AreaController> areas = new ArrayList<>();
	
	public RaceModel() {
		createFiches();
	}

	public void createFiches(){
		IntStream.range(0,fichesAantal).forEach(i -> {
			availableFiches.push(new RaceFiche());
		});
		beginCountFiches = fichesAantal;
	}

	public Stack<RaceFiche> getFiches(int count){
		Stack<RaceFiche> leaveFiches = new Stack<>();
		IntStream.range(0, count).forEach(i -> leaveFiches.push(availableFiches.pop()));
		return leaveFiches;
	}

	public void pushFiches(Stack<RaceFiche> fiches){
		while(fiches.size() > 0){
			availableFiches.push(fiches.pop());
		}
	}
	public List<AreaController> getAreas(){
		return areas;
	}

//	public void addFichesToArea(AreaController area, int amount){
////		for(int i = 0; i < amount)
////			availableFiches.pop();
////	}

	public int getFichesCount(){
		return availableFiches.size();
	}
	
	
}
