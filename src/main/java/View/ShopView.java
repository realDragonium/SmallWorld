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

    public void buyItem2(){
        shopCon.buyingItem(0);
    }

    public void buyItem3(){
        shopCon.buyingItem(0);
    }

    public void buyItem4(){
        shopCon.buyingItem(0);
    }

    public void buyItem5(){
        shopCon.buyingItem(0);
    }

    public void buyItem6(){
        shopCon.buyingItem(0);
    }

    public void initialize() {
        group.getChildren().add(pane);
    }
    public void showCombination(){

    }
}
