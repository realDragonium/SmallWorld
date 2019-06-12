package DiceMVC;

public interface Observable {
    void register(Observer ob);
    void notifyAllObs();
    int getWaarde();
}
