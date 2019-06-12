package DiceMVC;

import java.util.ArrayList;
import java.util.List;

public class DiceModel implements Observable {

    int gegooideOgen=0;
    List<Observer> lijst = new ArrayList<>();


    public void changeSide(int gegooideOgen){
        this.gegooideOgen = gegooideOgen;
        notifyAllObs();
    }

    @Override
    public void register(Observer ob) {
        lijst.add(ob);
    }



    @Override
    public void notifyAllObs() {
        for(Observer ob : lijst){
            ob.update(this);
    }}

    @Override
    public int getWaarde() {
        return gegooideOgen;
    }
}

