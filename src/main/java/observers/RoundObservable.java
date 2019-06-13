package main.java.observers;

public interface RoundObservable {
    public void register(RoundObserver ro);
    public void notifyAllObservers();
    public boolean playerOneTurn();
}
