package Controller;

import Model.RaceModel;
import Objects.Kracht;
import Objects.RaceFiche;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import Enum.TurnFase;
import java.util.Stack;

public class RaceController {

	private CombinationController combiCon;
	private Kracht kracht;
	private RaceModel model;

	RaceController(Kracht kracht, String id, int ficheAmount) {
		this.kracht = kracht;
		model = new RaceModel(id, ficheAmount);
		kracht.setRaceCon(this);
	}

	public List<AreaController> getAllAreas(){
		return model.getAreas();
	}

	int fichesCount(){
		return model.getFichesCount();
	}

	void setCombiCon(CombinationController combiCon){
		this.combiCon = combiCon;
	}

	Stack<RaceFiche> getFiches(int count){
		Stack<RaceFiche> tempFiches = model.removeFiches(count);
		updatePlayerFicheAmount();
		return tempFiches;
	}

	void pushFiches(Stack<RaceFiche> fiches){
		model.pushFiches(fiches);
		updatePlayerFicheAmount();
	}

	boolean hasEnoughFiches(int count){
		return count <= model.getFichesCount();
	}

	void fichesOver(){
		System.out.println(combiCon.getPlayer().getId() + " heeft " + model.getFichesCount() + " fiches.");
	}

	void doKractAction(){
		kracht.doAction();
	}

    void returnFiches() {
		for(AreaController area : model.getAreas()){
			area.returnAllButOne(this);
		}
		updatePlayerFicheAmount();
    }

	/**
	 * De methode destroyAllFichesButOne() roept in de RaceModel de methode removeAllFichesButOne() aan.
	 * De methode destroyAllFichesButOne() roept in de RaceController de methode updatePlayerFicheAmount() aan.
	 * @author : Martijn van der Steen.
	 * @version : Juni 2019
	 */

	void destroyAllFichesButOne(){
		model.removeAllFichesButOne();
		updatePlayerFicheAmount();
	}

    void addArea(AreaController area){
		model.addArea(area);
	}

	private void updatePlayerFicheAmount(){
		combiCon.getPlayer().setFiches(model.getFichesCount());
	}

	void removeArea(AreaController area) {
		model.removeArea(area);
	}

	public int getAreasAmount() {
		return model.getAreas().size();
	}

	public String getId(){
		return model.getId();
	}

	boolean checkPhaseActoin(TurnFase curPhase) {
		return kracht.checkPhaseAction(curPhase);
	}

    public CombinationController getCombiCon() {
		return combiCon;
    }

    public void addFiche(RaceFiche oneFiche) {
		model.addFiche(oneFiche);
		updatePlayerFicheAmount();
    }

	public RaceFiche removeFiche() {
		RaceFiche tempFiche = model.getFiche();
		updatePlayerFicheAmount();
		return tempFiche;

	}
}
