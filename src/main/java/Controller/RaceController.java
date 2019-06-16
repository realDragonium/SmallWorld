package Controller;

import Model.RaceModel;
import Objects.Kracht;
import Objects.RaceFiche;

import java.util.Stack;

public class RaceController {

	private Kracht kracht;
	private RaceModel model;
	private int fichesAantal = 15;

	public RaceController(Kracht kracht) {
		this.kracht = kracht;
		model = new RaceModel(fichesAantal);
		//playerCon.setFiches(fichesAantal);
	}

	public Stack<RaceFiche> getFiches(int count){
		//playerCon.lowerFiches(count);
		return model.getFiches(count);
	}

	public void pushFiches(Stack<RaceFiche> fiches){
		//playerCon.higherFiches(fiches.size());
		model.pushFiches(fiches);
	}

	public boolean hasEnoughFiches(int count){
		return count <= model.getFichesCount();
	}

	public void fichesOver(){
		//System.out.println(playerCon.getId() + " heeft " + model.getFichesCount() + " fiches.");
	}

	public void doKractAction(){
		kracht.doAction();
	}

}
