package controlers;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import models.AreaModel;
import models.FicheModel.ficheType;
import observers.AreaObserver;

public class AreaControler {
	private AreaModel model = new AreaModel();
	
	public AreaControler() {
		
	}
	
	public void configureTerrainData(Shape3D mesh) {
		model.configureData(mesh);
	}
	
	public int getAreaDefenceValue() {
		int defenceValue = 0;
		for(FicheControler fiche : model.getFichesOnArea()) {
			defenceValue += fiche.getDefenceValue();
		}
		return defenceValue;
	}
	
	public void changeStateHovering() {
		model.changeStateHovering();
	}
	
	public void registerObserver(AreaObserver ao) {
		model.register(ao);
	}
	
//	public RaceControler getAreaOwner() {
//		for(FicheControler fiche : model.getFichesOnArea()) {
//			if(fiche.getMyType() == ficheType.RAS) {
//				return fiche.getMyRace();
//			}
//		}
//	}
}
