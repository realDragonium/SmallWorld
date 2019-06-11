package MVC;

public interface Observable {
    void register(Observer ob);
    void notifyAllObs();
    String getWaarde();


}
