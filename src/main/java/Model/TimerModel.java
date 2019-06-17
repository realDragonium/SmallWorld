package Model;

import Controller.GameController;
import Observer.TimerObserver;
import observable.TimerObservable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimerModel implements TimerObservable, ChangeListener {

    int elapsedTime;
    TimerObserver observer;
    int timeAmount = 20;
    boolean timerDone;

    public boolean timerIsDone(){
        return(elapsedTime >= timeAmount);
    }

    public void addSecond(){
        elapsedTime++;
        notifyAllObservers();
    }

    @Override
    public void register(TimerObserver to) {
        observer = to;
    }

    @Override
    public void unregister(TimerObserver to) {
        observer = null;
    }

    @Override
    public int getSeconds() {
        return timeAmount - elapsedTime;
    }

    @Override
    public void notifyAllObservers() {
        observer.update(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
