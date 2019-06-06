package observable;

import observers.DepthObserver;
import javafx.scene.Group;

public interface DepthObservable {	
	void notifyObserver();
	void register(DepthObserver depthO);
	Group getGroup();
}
