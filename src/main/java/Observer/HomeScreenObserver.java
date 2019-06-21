package Observer;

import Observable.HomeScreenObservable;

/** This interface is the HomeScreenObserver which is implemented by the HomeScreenView class.
 *
 * @author:
 * @Version:
 */
public interface HomeScreenObserver {

    /**
     * @param hso has the HomeScreenObservable parameter which is used to update the HomeScreenView
     */
    public void update(HomeScreenObservable hso);
}
