package View;

import Controller.VervallenController;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class VervallenView {

    Group group;
    public Pane root;


    VervallenController vervalCon;
    TextField vervaltext;

    public VervallenView(VervallenController vervalCon, Group group){
        this.vervalCon = vervalCon;
        this.group = group;
    }
    public void initialize(){
        group.getChildren().addAll(root);
    }



    public void inVerval(){vervalCon.inVerval();}

}