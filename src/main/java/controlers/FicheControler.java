package controlers;

import models.FicheModel;

public class FicheControler {

	FicheModel model;
	
	public int getDefenceValue() {
		return model.whatsMyDeffenceValue();
	}
	
	public FicheModel.ficheType getMyType() {
		return model.whatsMyType();
	}
}
