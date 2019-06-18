package observers;

import observable.LoginObservable;

public interface LoginObserver {
	void update(LoginObservable lo);
}
