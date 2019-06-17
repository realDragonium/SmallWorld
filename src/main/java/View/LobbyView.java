package View;

import Controller.HomeScreenController;
import Controller.LobbyController;
import Observer.LobbyObserver;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LobbyView implements LobbyObserver {
    LobbyController lobbyCon;

    private Group group;
    public Group root;
    public Button terug, hosten;
    public TextField textfield1;
    public GridPane pane1;
    private String lobbyNaam;
    private Button activeButton;
    private Button[] lobbyQuantity = new Button[5];
    private int gridCounter = 0;

    public LobbyView(Group group, LobbyController con) {
        this.group = group;
        this.lobbyCon = con;
    }


    public void initialize() {
        group.getChildren().add(root);
        lobbyCon.register(this);
    }

    public void terug() {
        new HomeScreenController();
    }

    public void join(){
       // lobbyCon.startInLobbyScreen();
        lobbyCon.lobbyEdit();
    }

/*
    public void join(String lobbyNaam){
        lobbyCon.lobbyEdit(lobbyNaam);
        // lobbyCon.startInLobbyScreen(lobbyNaam);
    }
   */


    public void hostGame(ActionEvent t) {
        if (gridCounter < 5) {
            Button btn = new Button("lobby" + gridCounter);
            lobbyNaam = btn.getText();              // = lobbynaam
            //lobbyNaam = getLobbyNaam();
            pane1.setVgap(10);
            pane1.add(btn, 0, gridCounter);
            lobbyQuantity[gridCounter] = btn;
            gridCounter++;

            join();

            btn.setOnAction(d -> {
                if (activeButton != null) {
                    activeButton.setStyle(" -fx-background-color:   -fx-background");
                }
                btn.setStyle("-fx-background-color: yellow;");
                System.out.println("TESTTEST");
                activeButton = btn;
            });
        } else {
            ((Node)t.getSource()).setOpacity(0);
            root.getChildren().remove(t.getSource());
            System.out.println("Max aantal lobbies");
        }
    }

//    public String getLobbyNaam(){
//        return lobbyCon.getLobbyNaam();
//    }



    @Override
    public void update(boolean mode) {

    }

}
