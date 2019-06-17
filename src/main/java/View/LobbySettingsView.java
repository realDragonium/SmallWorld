package View;

import Controller.LobbySettingsController;
import Observer.LobbySettingsObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class LobbySettingsView implements LobbySettingsObserver{
    ObservableList list = FXCollections.observableArrayList();
    LobbySettingsController con;
    Group group;
    public Group root;
    private TextField lobbyNaam;

    public ChoiceBox<String> playerAmount;

    public LobbySettingsView(Group group, LobbySettingsController con) {
        this.group = group;
        this.con = con;
    }

    public void submit(){
        lobbyName();
    }

    public void getChoices(){
        list.removeAll(list);
        String a = "2";
        String b = "3";
        String c = "4";
        list.addAll(a, b, c);
        playerAmount.getItems().addAll(list);

    }





    public void lobbyName(){
        lobbyNaam.getText();
    }

    public void initialize() {
        group.getChildren().add(root);
        con.register(this);
    }








    @Override
    public void update() {
    }




}
