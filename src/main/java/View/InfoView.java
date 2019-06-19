package View;

import Controller.InfoController;
import Observer.infoObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import observable.infoObservable;

import java.awt.*;



public class InfoView implements infoObserver {
    Group infoGroup;
    InfoController infoCon;
    @FXML
    public Group pane;

    @FXML
    public TextArea infoText;

    @FXML
    public MenuItem  button;

    public InfoView(Group groep, InfoController infoController) {
        infoGroup = groep;
        infoCon = infoController;
        infoCon.register(this);
    }

    @FXML
    public void showInfo(){
        infoText.setText("test");
      }

    @Override
    public void update(infoObservable ob){
        infoText.setWrapText(true);
        infoText.setText(ob.currentText());

    }



    public void initialize() {
        System.out.println("test");
        infoGroup.getChildren().add(pane);
        System.out.println("test2");
    }

}
