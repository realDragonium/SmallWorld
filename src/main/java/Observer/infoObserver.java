package Observer;
import Observable.infoObservable;

/** This interface is the infoObserver which is implemented by the infoView class.
 * @author:
 * @Version:
 */

public interface infoObserver {
    /**
     * @param ob as the infoObserver parameter which is used to update the infoView
     */
public void update(infoObservable ob);
}
