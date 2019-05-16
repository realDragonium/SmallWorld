package moleculesampleapp;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class AnimPoint {
	Translate translate;
	Rotate rotateX;
	Rotate rotateY;
	Rotate rotateZ;
	double time;
	
	public AnimPoint(Translate translate, Rotate x, Rotate y, Rotate z, double time) {
		this.translate = translate;
		rotateX = x;
		rotateY = y;
		rotateZ = z;
		this.time = time;
	}
	
	
}
