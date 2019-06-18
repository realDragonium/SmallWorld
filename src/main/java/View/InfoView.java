package View;

import Controller.InfoController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;


public class InfoView {
    Group infoGroup;
    InfoController infoCon;
    @FXML
    Pane pane;

    public InfoView(Group groep, InfoController infoController) {
        infoGroup = groep;
        infoCon = infoController;
    }

    public void initialize() {infoGroup.getChildren().add(pane);
    }
}
