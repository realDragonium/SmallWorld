package Model;

import Controller.PlayerController;
import Objects.RaceFiche;
import Observable.AreaObservable;
import Observer.AreaObserver;

import java.util.Stack;
import java.util.stream.IntStream;

public class AreaModel implements AreaObservable {

    private Stack<RaceFiche> raceFiches = new Stack<>();
    private boolean active = false;
    private AreaObserver observer;
    private String id;
    public PlayerController player;

    public AreaModel(String id) {
        this.id = id;
        IntStream.range(0, (int) (Math.random() * 3)).forEach(i -> raceFiches.push(new RaceFiche()));
    }

    public String getId() {
        return id;
    }

    public void changeActive() {
        active = !active;
        notifyObserver();
    }

    //+2 vanwege 1 extra per fiche en het land is 2 verdedingspunt waard
    public int numbersNeeded() {
        return raceFiches.size() + 2;
    }

    //Ze worden overschreven omdat getAllFiches de hele lijst al mee geeft
    public void attackArea(Stack<RaceFiche> fiches) {
        raceFiches = fiches;
        notifyObserver();
    }

    public Stack<RaceFiche> getAllFiches() {
        return raceFiches;
    }

    @Override
    public void register(AreaObserver ao) {
        observer = ao;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public int getNumberOfFiches() {
        return raceFiches.size();
    }
}
