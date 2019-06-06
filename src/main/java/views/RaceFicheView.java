package views;

import java.io.IOException;

import controlers.RaceFicheControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import moleculesampleapp.Xform;
import observable.DepthObservable;
import observable.FicheObservable;
import observers.DepthObserver;
import observers.FicheObserver;

public class RaceFicheView implements FicheObserver, DepthObservable{
	
	private DepthObserver observer;
	Xform fiche3dModel = new Xform();
	RaceFicheControler controler = new RaceFicheControler();
	
	public RaceFicheView(String race, DepthObserver observer) {
		register(observer);
		get3dModel(race);
		controler.registerObserver(this);	
	}
	
	public void get3dModel(String race) {
		try {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fiches/" + race + ".fxml"));
		Group graphic = fxmlLoader.load();
		fiche3dModel.getChildren().add(graphic);
		}catch (IOException e) {
			e.printStackTrace();
		}	
		notifyObserver();
	}
	
	public void doBeginAnimation() {
		controler.playCoinFallingAnim(fiche3dModel);
	}
	
	@Override
	public void update(FicheObservable fo) {
		fiche3dModel.setTranslate(fo.getPosition());
	}

	@Override
	public void notifyObserver() {
		observer.update(this);	
	}

	@Override
	public void register(DepthObserver depthO) {
		observer = depthO;
	}

	@Override
	public Group getGroup() {
		return fiche3dModel;
	}

}
