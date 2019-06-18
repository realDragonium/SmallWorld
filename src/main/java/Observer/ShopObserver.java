package Observer;

import observable.ShopObservable;

public interface ShopObserver {
    void update(ShopObservable so);
}
