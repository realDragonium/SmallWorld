package Observable;

import Observer.InLobbyObserver;


public interface InLobbyObservable {
    void notifyAllObservers();
    void register(InLobbyObserver ob);
    void unregister(InLobbyObserver ob);

    String getPlayer1();
    String getPlayer2();
    String getPlayer3();
    String getPlayer4();





    boolean getStart();
}
