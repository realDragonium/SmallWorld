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

	public RaceController(Kracht kracht, String id, int ficheAmount) {
		this.kracht = kracht;
		model = new RaceModel(id, ficheAmount);
	}

	public int fichesCount(){
		return model.getFichesCount();
	}

	public void setCombiCon(CombinationController combiCon){
		this.combiCon = combiCon;
	}

	public Stack<RaceFiche> getFiches(int count){
		Stack<RaceFiche> tempFiches = model.removeFiches(count);
		updatePlayerFicheAmount();
		return tempFiches;
	}

	public void pushFiches(Stack<RaceFiche> fiches){
		model.pushFiches(fiches);
		updatePlayerFicheAmount();
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
		for(AreaController area : model.getAreas()){
			area.returnAllButOne(this);
		}
		updatePlayerFicheAmount();
    }

    public void destroyAllFichesButOne(){
		model.removeAllFichesButOne();
		updatePlayerFicheAmount();
	}

    public void addArea(AreaController area){
		model.addArea(area);
	}

	public void updatePlayerFicheAmount(){
		combiCon.getPlayer().setFiches(model.getFichesCount());
	}

	public void removeArea(AreaController area) {
		model.removeArea(area);
	}

	public int getAreasAmount() {
		return model.getAreas().size();
	}

	public String getId(){
		return model.getId();
	}
}
