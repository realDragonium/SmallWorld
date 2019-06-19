package observable;

import Observer.LeaderboardObserver;

public interface LeaderboardObservable {
    void register(LeaderboardObserver ob);
    void notifyAllObs();
    String getWaarde();
    String getPlace1();
    String getPlace2();
    String getPlace3();


}
