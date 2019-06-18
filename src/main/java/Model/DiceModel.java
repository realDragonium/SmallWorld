package Model;


import Observer.DiceObserver;
import observable.DiceObservable;

import java.util.ArrayList;

import java.util.List;

public class DiceModel implements DiceObservable {

    int gegooideOgen = 0;


    List<DiceObserver> observers = new ArrayList<>();


    public void changeSide(int gegooideOgen) {
        this.gegooideOgen = gegooideOgen;

        notifyAllObs();
    }

    public void giveValue(int gegooideOgen) {
        if (gegooideOgen == 0) {
            gegooideOgen = 0;
        } else if (gegooideOgen == 1) {
            gegooideOgen = 0;
        } else if (gegooideOgen == 2) {
            gegooideOgen = 0;
        } else if (gegooideOgen == 3) {
            gegooideOgen = 1;
        } else if (gegooideOgen == 4) {
            gegooideOgen = 2;
        } else if (gegooideOgen == 5) {
            gegooideOgen = 3;
        } else System.out.println("joe");
    }

    public void register(DiceObserver ob) {
        observers.add(ob);
    }


    @Override
    public void notifyAllObs() {
        for (DiceObserver ob : observers) {
            ob.update(this);
        }
    }

    @Override
    public int getWaarde() {
        return gegooideOgen;
    }

}

