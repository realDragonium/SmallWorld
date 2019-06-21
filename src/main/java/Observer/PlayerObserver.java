package Observer;

import Observable.PlayerObservable;

/** This interface is the LobbyObserver which is implemented by the lobbyView class.
 * @author Yoran de Vos
 * @Version June 2019
 */
public interface PlayerObserver {

    /**
     * @param po has the PlayerObservable parameter which is used to update the playerView
     */
    void update(PlayerObservable po);
}
