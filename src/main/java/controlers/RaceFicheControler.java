package controlers;

import moleculesampleapp.Xform;
import views.RaceFicheView;

public class RaceFicheControler {
	public FicheControler fiche = new FicheControler();
	
	public void playCoinFallingAnim(Xform xform) {
		fiche.setAnimation("Coin Falling", xform);
	}

	public void registerObserver(RaceFicheView raceFicheView) {
		fiche.registerObserver(raceFicheView);
	}
}
