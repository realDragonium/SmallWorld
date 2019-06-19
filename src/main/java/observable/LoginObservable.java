package Observable;

import Observer.LoginObserver;

public interface LoginObservable {
    void register(LoginObserver mvo);
    void unregister(LoginObserver mvo);
    boolean getLoginState();
    void notifyAllObservers();
}
