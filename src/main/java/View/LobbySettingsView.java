package View;

import Controller.LobbySettingsController;
import Observer.LobbySettingsObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbySettingsView implements LobbySettingsObserver{
    ObservableList list = FXCollections.observableArrayList("2 players", "3 players", "4 players");
    LobbySettingsController con;
    Group group;
    public Group root;
    public TextField lobbyNaam;
    public ChoiceBox<String> box;
    private String textLobby;
    public Button hosten;
    public Button test;

    public LobbySettingsView(Group group, LobbySettingsController con) {
        this.group = group;
        this.con = con;

    }

    public void submit(){
        //setLobbyName(lobbyNaam.getText());

        con.lobbyView();   // gaat terug naar lobby overzicht
    }

    public void back(){
        con.lobbyView();
    }

//    public void setLobbyName(String lobbyNaam){
//        con.setLobbyName(lobbyNaam);
//    }



    public void playerAmountBox(){
        box.setItems(list);
        box.setValue("Select Lobby Size");
    }

    public void lobbyName(){

    }

    public void initialize() {
        group.getChildren().add(root);
        con.register(this);
        playerAmountBox();

    }



    @Override
    public void update() {
    }




}
