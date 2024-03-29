package models;

import java.util.ArrayList;
import java.util.List;

import observable.LoginObservable;
import observers.LoginObserver;

public class LoginModel implements LoginObservable {
	private List<LoginObserver> observers = new ArrayList<>();
	private boolean loginAccepted = false;
	
	@Override
	public void notifyAllObservers() {
		for(LoginObserver lo : observers) {
			lo.update(this);
		}
	}

	@Override
	public void register(LoginObserver lo) {
		observers.add(lo);
		
	}

	@Override
	public void unregister(LoginObserver mvo) {
		observers.remove(mvo);
	}

	public void loginAccepted(boolean result) {
		loginAccepted = result;
		notifyAllObservers();
	}

	@Override
	public boolean getLoginState() {
		// TODO Auto-generated method stub
		return loginAccepted;
	}

}