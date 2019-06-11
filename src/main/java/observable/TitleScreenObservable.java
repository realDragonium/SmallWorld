package observable;

import observers.TitleScreenObserver;

public interface TitleScreenObservable {
	
	void notifyAllObservers();
	void register(TitleScreenObserver ob);

}
