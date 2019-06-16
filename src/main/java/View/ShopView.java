package View;

import Controller.ShopController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class ShopView {

    @FXML
    public Pane pane;

    ShopController shopCon;
    Group group;

    public ShopView(Group group, ShopController shopCon){
        this.shopCon = shopCon;
        this.group = group;
    }

    public void buyItem1(){
        shopCon.buyingItem(0);
    }

    public void initialize() {
        group.getChildren().add(pane);
    }
    public void showCombination(){

    }
}
