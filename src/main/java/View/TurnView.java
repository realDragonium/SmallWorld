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
import javafx.scene.text.Text;


/**@author Yoran
 *
 */

public class TurnView implements TurnObserver {

    private Group group;
    private TurnController turnCon;

    @FXML
    public Group groupFXML;
    public Text turnField;
    public Text faseField;

    /**
     *
     * @param group wordt in de constructor meegegeven om alle omvattende nodes uit het fxml mee te sturen
     * @param turnCon is een variable van de TurnController waar alle logica staat om van fase en ronde te veranderen
     */


    public TurnView(Group group, TurnController turnCon){
        this.group = group;
        this.turnCon = turnCon;
    }

    public void initialize() {
        group.getChildren().add(groupFXML);
        turnCon.register(this);
    }


    /**
     *
     * @param getal is meegegeven integer om de juiste ronde te zetten
     * @param fase
     */

    private void setTextFields(int getal, TurnFase fase){
        turnField.setText("" + getal);
        faseField.setText("" + fase.toString());
    }

    @Override
    public void update(TurnObservable to) {
        setTextFields(to.getTurn(), to.getFase());
    }
}
