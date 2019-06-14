package views;

import javafx.scene.shape.Shape;

import controlers.AreaController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import observable.AreaObservable;
import observers.AreaObserver;

public class AreaView implements AreaObserver{
	
	private AreaController areaController;
	private String areaId;
	private Color nonActive;
	private Color active;
	private Node area;
	
	public AreaView(AreaController controller, Node area, String areaId) {
		areaController = controller;	
		this.areaId = areaId;
		controller.registerView(this, areaId);
		setActionEvents(area);
		this.area = area;
		nonActive = (Color) ((Shape) area).getFill();
		active = nonActive.darker();
	}
	
	public void setActionEvents(Node node) {
		node.setOnMouseClicked(e -> {
			System.out.println(node.getId() + " geklikt");
			areaController.AreaClicked(areaId);
		});
	}

	@Override
	public void update(AreaObservable ao) {
		if(ao.isActive()) {
			((Shape) area).setFill(active);
		}
		else {
			((Shape) area).setFill(nonActive);
		}
		
	}
	
}
