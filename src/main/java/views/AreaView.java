package views;

import controlers.AreaControler;
import Observable.AreaObservable;
import Observer.AreaObserver;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

public class AreaView implements AreaObserver {
	Shape3D mesh;
	PhongMaterial material;
	AreaControler controler = new AreaControler();

	public AreaView(Shape3D mesh) {
		this.mesh = mesh;
		material = (PhongMaterial) mesh.getMaterial();
		controler.configureTerrainData(mesh);
		controler.registerObserver(this);
		confActions();
	}
	
	public void confActions() {
		mesh.setOnMouseEntered(e -> {
			controler.changeStateHovering();
		});
		
		mesh.setOnMouseExited(e ->{
			controler.changeStateHovering();
		});
	}
	
	
	@Override
	public void update(AreaObservable ao) {
		if(ao.isHovering()) {
			makeHighlighted();
		}
		else {
			makeNotHighlighted();
		}
	}
	
	private void makeHighlighted() {
		material.setDiffuseColor(material.getDiffuseColor().brighter());
	}
	
	private void makeNotHighlighted() {
		material.setDiffuseColor(material.getDiffuseColor().darker());
	}
	
}