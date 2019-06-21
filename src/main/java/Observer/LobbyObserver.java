
package Observer;

import Observable.ObservableLobby;

/** This interface is the LobbyObserver which is implemented by the lobbyView class.
 * @author Lars Puente Blom
 * @Version June 2019
 */

public interface LobbyObserver {

	/**
	 * @param lo has the LobbyObserver parameter which is used to update the lobbyView
	 */
	void update(ObservableLobby lo);

}
