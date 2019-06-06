package views;

import controlers.AreaControler;
import controlers.FicheControler;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;
import moleculesampleapp.Main;
import moleculesampleapp.Xform;
import observers.AreaObservable;
import observers.AreaObserver;

public class AreaView implements AreaObserver {
	Shape3D mesh;
	PhongMaterial material;
	Color selectedColor;
	Color normalColor;
	public AreaControler controler;
	Xform number;

	public AreaView(Shape3D mesh, Translate centerPoint) {
		this.mesh = mesh;
		material = (PhongMaterial) mesh.getMaterial();
		controler = new AreaControler();
		controler.configureTerrainData(mesh);
		controler.setCenterPoint(centerPoint);
		controler.registerObserver(this);
		normalColor = material.getDiffuseColor();
		selectedColor = material.getDiffuseColor().brighter();
		confActions();
		createNumber();
		
	}
	
	public void createNumber() {
		number = new Xform();
        Box box = new Box();
        number.getChildren().add(box);
        
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(new Color(0.4,0.2,0.2,0.3));
        redMaterial.setSpecularColor(Color.WHITE);
        box.setMaterial(redMaterial);
        number.setOpacity(0.1);
        Main.World.getChildren().add(number);
        
        number.setScale(0.15);
        number.setTranslate(controler.getCenterPoint());
        number.setVisible(false);
	}
	
	public void confActions() {
		mesh.setOnMouseEntered(e -> {
			controler.changeStateHovering();
		});
		
		mesh.setOnMouseExited(e ->{
			controler.changeStateHovering();
		});
		
		mesh.setOnMouseClicked(e ->{
			Xform fiche = new Xform();
            Box box = new Box();
	        fiche.getChildren().add(box);
            
	        final PhongMaterial redMaterial = new PhongMaterial();
	        redMaterial.setDiffuseColor(Color.DARKRED);
            redMaterial.setSpecularColor(Color.RED);
            
            box.setMaterial(redMaterial);
            
            fiche.setScale(0.1);
            Main.World.getChildren().add(fiche);
            FicheView ficheView = new FicheView(fiche);
            ficheView.controler.setAnimationControler(controler.getAnimControler());
            controler.addFicheToArea(ficheView.controler);
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
		number.setTranslate(new Translate(controler.getCenterPoint().getX(),controler.getCenterPoint().getY() -(ao.getRaceFichesAmount() + 1) * 0.2, controler.getCenterPoint().getZ()));
		
		System.out.println("ras fiches zijn: " + ao.getRaceFichesAmount());
		if(ao.getRaceFichesAmount() > 0) {
			number.setVisible(true);
		}
		else{
			number.setVisible(false);
		}
	}
	
	private void makeHighlighted() {
		material.setDiffuseColor(selectedColor);
	}
	
	private void makeNotHighlighted() {
		material.setDiffuseColor(normalColor);
	}
	
}
