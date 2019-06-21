package Model;

import Enum.TurnFase;
import Observable.TurnObservable;
import Observer.TurnObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TurnModel implements TurnObservable {
    private List<TurnObserver> observers = new ArrayList<>();
    public int currentTurn;
    private int turnPerRound;
    public TurnFase fase;
    private Queue<TurnFase> fases = new LinkedList<>();


    public TurnModel(int turnPerRound){
        this.turnPerRound = turnPerRound;
        currentTurn = 1;
        makeStack();
    }

    public int getTurnPerRound(){
        return turnPerRound;
    }

    public void makeStack(){
        for(TurnFase fase: TurnFase.values()){
            fases.add(fase);
        }
        fase = fases.remove();
    }

    public void nextTurn(){
        currentTurn++;
        notifyObservers();
    }

    @Override
    public void register(TurnObserver to) {
        observers.add(to);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        observers.forEach(ob -> ob.update(this));
    }

    @Override
    public int getTurn() {
        return currentTurn;
    }

    @Override
    public TurnFase getFase() {
        return fase;
    }

    public void setFase(TurnFase currentPhase) {
        fase = currentPhase;
    }
}
