package Model;

import Observer.TimerObserver;
import Observable.TimerObservable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimerModel implements TimerObservable, ChangeListener {

    int elapsedTime;
    TimerObserver observer;
    int timeAmount = 10;
    boolean timerDone = false;

    public TimerModel(){

    }

    public boolean timerIsDone(){
        return timerDone;
    }

    public void addSecond(){
        elapsedTime++;
        if(elapsedTime >= timeAmount){
            timerDone = true;
        }
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
