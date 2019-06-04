package moleculesampleapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.io.IOException;

public class Model {
    Group graphic;

    @SuppressWarnings("deprecation")
    public void importFXMl(String assetPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource(assetPath));
        graphic = fxmlLoader.load();
    }

    public Group getGraphic() {
        return graphic;
    }
}
