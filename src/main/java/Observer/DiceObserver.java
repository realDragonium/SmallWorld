package Observer;

import Observable.DiceObservable;

/** This interface is the DiceObserver which is implemented by the DiceView class.
 *
 * @author:
 * @Version:
 */
public interface DiceObserver {

    /**
     * @param ob has the DiceObservable parameter which is used to update the DiceView
     */
    void update(DiceObservable ob);



}
