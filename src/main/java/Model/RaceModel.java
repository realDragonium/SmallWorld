package Model;

import Objects.RaceFiche;

import java.util.Stack;
import java.util.stream.IntStream;

public class RaceModel {

	private int beginCountFiches;
	
	private Stack<RaceFiche> availableFiches = new Stack<>();
	
	public RaceModel(int aantal) {
		IntStream.range(0,aantal).forEach(i -> {
			availableFiches.push(new RaceFiche());
		});
		beginCountFiches = aantal;
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

	public int getFichesCount(){
		return availableFiches.size();
	}
	
	
}
