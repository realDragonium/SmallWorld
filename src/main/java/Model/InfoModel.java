package Model;

import Observer.infoObserver;

import java.util.ArrayList;
import java.util.List;
import observable.infoObservable;

public class InfoModel implements infoObservable {
    List<infoObserver> infoObservers = new ArrayList<>();

    @Override
    public void register(infoObserver ob){
       infoObservers.add(ob);
   }
    @Override
    public void notifyAllObs(){
    for(infoObserver obs: infoObservers ){
        obs.update(this);
    }
    }
    @Override
    public String currentText(){
       return "";
    }
}
