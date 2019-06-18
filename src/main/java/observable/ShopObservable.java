package observable;

import Observer.ShopObserver;

public interface ShopObservable {
    void register(ShopObserver so);
    void notifyObservers();
    int getRound();
    String getPlayer(int item);
    String getPower(int item);
}
