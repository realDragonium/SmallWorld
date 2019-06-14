package observers;

import models.DiceModel;
import observable.DiceObservable;

public interface DiceObserver {
	void update(DiceObservable ob);
}
