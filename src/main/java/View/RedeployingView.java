package View;

import Controller.GameController;
import Controller.RedeployingController;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class RedeployingView {

    Group group;

    public Pane pane;

    RedeployingController redCon;

    public RedeployingView(RedeployingController redCon, Group group){
        this.redCon = redCon;
        this.group = group;
    }


    public void initialize(){
        group.getChildren().add(pane);
    }

    public void removeButton() {
        redCon.removeFiche();
    }

    public void addButton() {
        redCon.addFiche();
    }
}
