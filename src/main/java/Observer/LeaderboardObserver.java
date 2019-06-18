package Observer;

import observable.LeaderboardObservable;

public interface LeaderboardObserver {
    public void update(LeaderboardObservable ob);
}
