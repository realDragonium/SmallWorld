package models;

import java.util.ArrayList;
import java.util.List;

import observable.DiceObservable;
import observers.DiceObserver;

public class DiceModel implements DiceObservable {

    int gegooideOgen=0;
    List<DiceObserver> lijst = new ArrayList<>();


    public void changeSide(int gegooideOgen){
        this.gegooideOgen = gegooideOgen;
        notifyAllObs();
    }

    @Override
    public void register(DiceObserver ob) {
        lijst.add(ob);
    }



    @Override
    public void notifyAllObs() {
        for(DiceObserver ob : lijst){
            ob.update(this);
    }}

    @Override
    public int getWaarde() {
        return gegooideOgen;
    }
}
