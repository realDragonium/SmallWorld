package controlers;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import models.AnimPointModel;

public class AnimPointControler {
	public AnimPointModel model;
	
	public AnimPointControler() {
		model = new AnimPointModel();
	}
	
	public void setTranslate(Translate translate) {
		model.translate = translate;
	}
	
	public void setRotation(double rotX, double rotY, double rotZ) {
		model.rotateX = new Rotate(rotX);
		model.rotateY = new Rotate(rotY);
		model.rotateZ = new Rotate(rotZ);
	}
	
	public void setTime(int time) {
		model.time = time;
	}
}
