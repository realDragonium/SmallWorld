package Observer;

import Observable.HomeScreenObservable;

/** This interface is the HomeScreenObserver which is implemented by the HomeScreenView class.
 *
 * @author Yoran de Vos
 * @Version June 2019
 */
public interface HomeScreenObserver {

    /**
     * @param hso has the HomeScreenObservable parameter which is used to update the HomeScreenView
     */
    public void update(HomeScreenObservable hso);
}
