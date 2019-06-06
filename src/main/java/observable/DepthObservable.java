package observable;

import javafx.scene.Group;
import observers.DepthObserver;

public interface DepthObservable {	
	void notifyObserver();
	void register(DepthObserver depthO);
	Group getGroup();
}
