package controlers;

import models.ApplicatieModel;
import observers.ModelViewObserver;
import javafx.scene.Scene;

public class ApplicatieController {
	
	static ApplicatieController appCon = new ApplicatieController();
	
	private ApplicatieModel appModel = new ApplicatieModel();
	
	public void registerObserver(ModelViewObserver mvo) {
		appModel.register(mvo);
	}
	
	public static ApplicatieController getInstance() {
		return appCon;
	}

	public void setActiveScene(Scene scene) {
	}
}
