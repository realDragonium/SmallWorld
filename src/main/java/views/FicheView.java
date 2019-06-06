package views;

import controlers.FicheControler;
import moleculesampleapp.Xform;
import observers.FicheObservable;
import observers.FicheObserver;

public class FicheView implements FicheObserver{
	
	Xform fiche3dModel;
	FicheControler controler;
	
	public FicheView(Xform xform) {
		fiche3dModel = xform;
		controler = new FicheControler(xform);
		controler.registerObserver(this);
	}

	@Override
	public void update(FicheObservable fo) {
		fiche3dModel.setTranslate(fo.getPosition());
		System.out.println(fo.getPosition());
	}

}
