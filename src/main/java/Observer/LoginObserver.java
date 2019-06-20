package Observer;

import Observable.LoginObservable;

public interface LoginObserver {
	void update(LoginObservable lo);
}
