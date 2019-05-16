package moleculesampleapp;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class Object {
	Transform transform;
	Group graphic;
	
	@SuppressWarnings("deprecation")
	public void importFXMl(String assetPath) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource(assetPath));
        graphic = fxmlLoader.<Group>load();    
        transform = new Transform();
        transform.position = new Vector3(graphic.getTranslateX(), graphic.getTranslateY(), graphic.getTranslateZ());
        transform.rotation = new Rotation(graphic.impl_getPivotX(), graphic.impl_getPivotY(), graphic.impl_getPivotZ());
	}
	
	public Group getGraphic() {
		return graphic;
	}
	
	public void updatePosition() {
		graphic.setTranslateX(transform.position.x);
		graphic.setTranslateY(transform.position.y);
		graphic.setTranslateZ(transform.position.z);
	}
}
