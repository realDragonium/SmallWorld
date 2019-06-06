package controlers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Translate;
import models.FicheModel;
import moleculesampleapp.Main;
import moleculesampleapp.Xform;
import views.FicheView;

public class FicheControler {

	FicheModel model;
	AnimationsControler animControler;
	
	public FicheControler(Xform xform) {
		model = new FicheModel(xform);
	}
	
	public void setAnimationControler(AnimationsControler animControler) {
		this.animControler = animControler;
		System.out.println("animator gezet");
	}
	
	public int getDefenceValue() {
		return model.whatsMyDeffenceValue();
	}
	
	public FicheModel.ficheType getMyType() {
		return model.whatsMyType();
	}
	
	public void setAnimation(String animation){
		animControler.animateObject(animControler.getAnimation(animation), this.model.getXform());
	}
	
	public void setPosition(Translate pos) {
		model.setPosition(pos);
	}

	public void registerObserver(FicheView ficheView) {
		model.register(ficheView);
		
	}
}
