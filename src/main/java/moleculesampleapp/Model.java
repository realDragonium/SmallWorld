package moleculesampleapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.io.IOException;

public class Model {
    Transform transform;
    Group graphic;

    @SuppressWarnings("deprecation")
    public void importFXMl(String assetPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource(assetPath));
        graphic = fxmlLoader.load();
        transform = new Transform();
        transform.position = new Vector3(graphic.getTranslateX(), graphic.getTranslateY(), graphic.getTranslateZ());
        transform.rotation = new Rotation(graphic.impl_getPivotX(), graphic.impl_getPivotY(), graphic.impl_getPivotZ());
    }

    public Group getGraphic() {
        return graphic;
    }

    public void updateTransform(Rotation deltaRot) {
        graphic.setTranslateX(transform.position.x);
        graphic.setTranslateY(transform.position.y);
        graphic.setTranslateZ(transform.position.z);
        System.out.println("x as: " + deltaRot.x + " y as: " + deltaRot.y + " z as: " + deltaRot.z);
        graphic.getTransforms().add(new Rotate(deltaRot.x, deltaRot.y, deltaRot.z));
    }
}
