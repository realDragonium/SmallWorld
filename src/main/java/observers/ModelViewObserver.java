package observers;

import observable.ModelViewObservable;

public interface ModelViewObserver {
	void update(ModelViewObservable mvo);
}
