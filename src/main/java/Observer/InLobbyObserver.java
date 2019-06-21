package Observer;

import Observable.InLobbyObservable;

/** This interface is the InlobbyObserver which is implemented by the inlobbyView class.
 * @author:
 * @Version:
 */

public interface InLobbyObserver {

    /**
     * @param ilo as the InLobbyObservable parameter which is used to update the inlobbyView
     */
    void update(InLobbyObservable ilo);
}
