package View;

import Controller.ShopController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *  Gemaakt door Yoran op 17-6-2019.
 *  ShopView class weergeeft a.h.v. een FXML tag de buttons, waardoor de speler een ras kan kopen.
 *
 */


public class ShopView {

    @FXML
    public Pane pane;
    public Button buy1;
    public Button buy2;
    public Button buy3;
    public Button buy4;
    public Button buy5;
    public Button buy6;

    private Button lastActiveButton;
    ShopController shopCon;
    Group group;

    public ShopView(Group group, ShopController shopCon){
        this.shopCon = shopCon;
        this.group = group;
    }

    @FXML
    public void buyItem(){
        shopCon.buyingItem(Integer.parseInt(lastActiveButton.getId()));
    }

    @FXML
    public void setActive(MouseEvent e){
        lastActiveButton = (Button)e.getSource();
    }


    public void initialize() {
        group.getChildren().add(pane);
    }


    public void showCombination(){

    }
}
