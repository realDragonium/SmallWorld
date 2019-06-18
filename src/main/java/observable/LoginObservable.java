package observable;

import observers.LoginObserver;

public interface LoginObservable {
    void register(LoginObserver mvo);
    void unregister(LoginObserver mvo);
    boolean getLoginState();
    void notifyAllObservers();
}
