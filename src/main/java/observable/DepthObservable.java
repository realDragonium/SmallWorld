package Observable;

import Observer.DepthObserver;
import javafx.scene.Group;

public interface DepthObservable {	
	void notifyObserver();
	void register(DepthObserver depthO);
	Group getGroup();
}
