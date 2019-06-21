package Observer;

import Observable.AreaObservable;

/** This interface is the AreaObserver which is implemented by the AreaView class.
 *
 * @author
 * @Version June 2019
 */
public interface AreaObserver {

    /**
     * @param ao has the AreaObservable parameter which is used to update the AreaView
     */
    void update(AreaObservable ao);
}
