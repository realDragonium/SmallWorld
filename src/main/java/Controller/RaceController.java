package Controller;

import Model.RaceModel;
import Objects.Kracht;
import Objects.RaceFiche;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RaceController {

	private CombinationController combiCon;
	private Kracht kracht;
	private RaceModel model;
	private int fichesAantal = 15;
	private List<AreaController> areas = new ArrayList<>();

	public RaceController(Kracht kracht) {
		this.kracht = kracht;
		model = new RaceModel(fichesAantal);
	}

	public int fichesCount(){
		return model.getFichesCount();
	}

	public void setCombiCon(CombinationController combiCon){
		this.combiCon = combiCon;
	}

	public Stack<RaceFiche> getFiches(int count){
		combiCon.getPlayer().lowerFiches(count);
		return model.getFiches(count);
	}

	public void pushFiches(Stack<RaceFiche> fiches){
		combiCon.getPlayer().higherFiches(fiches.size());
		model.pushFiches(fiches);
	}

	public boolean hasEnoughFiches(int count){
		return count <= model.getFichesCount();
	}

	public void fichesOver(){
		System.out.println(combiCon.getPlayer().getId() + " heeft " + model.getFichesCount() + " fiches.");
	}

	public void doKractAction(){
		kracht.doAction();
	}

    public void returnFiches() {
		for(AreaController area : areas){
			area.returnAllButOne(this);
		}
    }
}
