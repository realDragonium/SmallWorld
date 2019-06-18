package View;

import Controller.RoundController;
import Controller.TurnController;
import Observable.RoundObservable;
import Observable.TurnObservable;
import Observer.TurnObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import Enum.TurnFase;

public class TurnView implements TurnObserver {

    private Group group;
    private TurnController turnCon;

    @FXML
    public Group groupFXML;
    public TextField turnField;
    public TextField faseField;


    public TurnView(Group group, TurnController turnCon){
        this.group = group;
        this.turnCon = turnCon;
    }

    public void initialize() {
        group.getChildren().add(groupFXML);
        turnCon.register(this);
    }

    private void setTextFields(int getal, TurnFase fase){
        turnField.setText("Turn: "+ getal);
        faseField.setText("Fase: " + fase.toString());
    }

    @Override
    public void update(TurnObservable to) {
        setTextFields(to.getTurn(), to.getFase());
    }
}
