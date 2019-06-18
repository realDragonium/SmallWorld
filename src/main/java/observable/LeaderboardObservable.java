package Observable;

import Observer.LeaderboardObserver;

public interface LeaderboardObservable {
    void register(LeaderboardObserver ob);
    void notifyAllObs();
    String getWaarde();


}
