package Observer;

import Observable.ShopObservable;

/** This interface is the ShopObserver which is implemented by the shopView class.
 * @author:
 * @Version:
 */

public interface ShopObserver {

    /**
     * @param so has the ShopObservable parameter which is used to update the shopView
     */
    void update(ShopObservable so);
}
