package observable;

import observers.PlayerObserver;

public interface PlayerObservable {
	void register(PlayerObserver mvo);
    void unregister(PlayerObserver mvo);
    void notifyAllObservers();
    int getPuntenAmount();
}
