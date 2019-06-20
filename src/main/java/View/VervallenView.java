package View;

import Controller.VervallenController;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class VervallenView {

    /**
     * @author : Martijn van der Steen
     * @version : Juni 2019
     */
    Group group;
    public Pane root;

    VervallenController vervalCon;
    TextField vervaltext;

    public VervallenView(VervallenController vervalCon, Group group){
        this.vervalCon = vervalCon;
        this.group = group;
    }

    /**
     * De methode initialize zorgt ervoor dat de pane aan de Group wordt toegevoegt.
     */
    public void initialize(){
        group.getChildren().addAll(root);
    }

    /**
     * De methode inVerval() wordt aangeroepen door een (button)onClick action.
     */
    public void inVerval(){vervalCon.inVerval();}

}