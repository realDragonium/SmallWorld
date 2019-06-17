package DiceMVC;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiceModel implements Observable {

    int gegooideOgen = 0;


    List<Observer> lijst = new ArrayList<>();


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
        System.out.println("De waarde is" + gegooideOgen);
    }

    public void playerValue(int gegooideOgen) {
        HashMap<Integer, String> waardeSpeler = new HashMap<Integer, String>();


    }

    @Override
    public void register(Observer ob) {
        lijst.add(ob);
    }


    @Override
    public void notifyAllObs() {
        for (Observer ob : lijst) {
            ob.update(this);
        }
    }

    @Override
    public int getWaarde() {
        return gegooideOgen;
    }

}

