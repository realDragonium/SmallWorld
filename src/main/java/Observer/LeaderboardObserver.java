package Observer;

import Observable.LeaderboardObservable;

/** This interface is the LeaderbordObserver which is implemented by the leaderboardView class.
 * @author:
 * @Version:
 */
public interface LeaderboardObserver {

    /**
     * @param ob as the LeaderboardObservable parameter which is used to update the leaderboardView
     */
    public void update(LeaderboardObservable ob);
}
