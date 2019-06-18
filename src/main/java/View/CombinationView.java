package View;

import Controller.CombinationController;
import Controller.PlayerController;
import javafx.scene.Group;

public class CombinationView {

    CombinationController comboCon;
    Group group;

    public CombinationView(Group group, CombinationController comboCon){
        this.comboCon = comboCon;
        this.group = group;
    }


}
