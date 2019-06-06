package controlers;

import models.AreaModel;
import Observer.AreaObserver;
import javafx.scene.shape.Shape3D;

public class AreaControler {
	private AreaModel model = new AreaModel();
	
	public AreaControler() {
		
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
//		for(FicheControler fiche : model.getFichesOnArea()) {
//			if(fiche.getMyType() == ficheType.RAS) {
//				return fiche.getMyRace();
//			}
//		}
//	}

}
