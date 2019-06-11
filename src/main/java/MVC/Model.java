package MVC;

import java.util.ArrayList;
import java.util.List;

public class Model implements Observable{

    String waarde = "";

    List<Observer> lijst = new ArrayList<>();

    public void addValue(){
        waarde="groetjes";
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
        }
    }

    @Override
    public String getWaarde() {
        return waarde;
    }
}
