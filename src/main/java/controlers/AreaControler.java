package controlers;

import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;
import models.AreaModel;
import observers.AreaObserver;

public class AreaControler {
	private AreaModel model = new AreaModel();
	AnimationsControler animControler;

	public void setCenterPoint(Translate point) {
		model.setCenterPoint(point);
	}

	public Translate getCenterPoint() {
		return model.getCenterPoint();
	}

	public void setAnimationsControler(AnimationsControler animControler) {
		this.animControler = animControler;
	}

	public AnimationsControler getAnimControler() {
		return animControler;
	}

	public void setFichePosition(FicheControler fiche) {
		fiche.setPosition(new Translate(model.getCenterPoint().getX(), model.getCenterPoint().getY() - (model.getFichesOnArea().size() - 1) * 0.2, model.getCenterPoint().getZ()));
	}

	public void addFicheToArea(FicheControler controler) {
		model.addFiche(controler);
		System.out.println("Aantal fiches: " + model.getFichesOnArea().size());
		setFichePosition(controler);
	}

	public void configureTerrainData(Shape3D mesh) {
		model.configureData(mesh);
	}

	public void changeStateHovering() {
		model.changeStateHovering();
	}

	public void registerObserver(AreaObserver ao) {
		model.register(ao);
	}

//	public RaceControler getAreaOwner() {
//		for(RaceFicheControler fiche : model.getFichesOnArea()) {
//			if(fiche.getMyType() == ficheType.RAS) {
//				return fiche.getMyRace();
//			}
//		}
//	}

}
