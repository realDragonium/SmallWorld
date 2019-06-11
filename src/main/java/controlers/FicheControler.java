package controlers;

import javafx.scene.transform.Translate;
import models.FicheModel;
import moleculesampleapp.Xform;
import observers.FicheObserver;
import views.RaceFicheView;

public class FicheControler {

	FicheModel model = new FicheModel();
	AnimationsControler animControler;
	
	public FicheControler() {
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
	
	public void setAnimation(String animation, Xform xform){
		animControler.animateObject(animControler.getAnimation(animation), xform);
	}
	
	public void setPosition(Translate pos) {
		model.setPosition(pos);
	}

	public void registerObserver(FicheObserver ficheView) {
		model.register(ficheView);
	}
}
