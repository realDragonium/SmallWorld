package Observable;
import Observer.infoObserver;
public interface infoObservable {
    void register(infoObserver ob);

    void notifyAllObs();

    String currentText();

}
