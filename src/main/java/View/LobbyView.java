package View;

import Controller.HomeScreenController;
import Controller.LobbyController;
import Observer.LobbyObserver;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import observable.LobbyObservable;
import java.util.List;

public class LobbyView implements LobbyObserver {
    LobbyController lobbyCon;
    private Group group;
    public Group root;
    public Button terug, hosten;
    public GridPane panel;
    private Button activeButton;
    //private Button[] lobbyQuantity = new Button[5];
    private int gridCounter = 0;
    private GridPane grid= new GridPane();

    public LobbyView(Group group, LobbyController con) {
        this.group = group;
        this.lobbyCon = con;
    }

    public void initialize() {
        group.getChildren().add(root);
        lobbyCon.register(this);
        panel.setVgap(10);
        List<String> lijst = lobbyCon.getFirebaseLobbyNamen();

        for(String lobbyNaam: lijst) {
            Button btn = new Button(lobbyNaam);
            panel.add(btn, 0, gridCounter);
            panel.getChildren().get(gridCounter).setId("button" + gridCounter);
            gridCounter++;
            btn.setOnAction(d -> {
                if (activeButton != null) {
                    activeButton.setStyle(" -fx-background-color:   -fx-background");
                }
                btn.setStyle("-fx-background-color: red;");
                activeButton = btn;
            });
        }
    }

    public void terug() {
        new HomeScreenController();
    }

    public void join() {
        lobbyCon.lobbyEdit();               // start de LobbySettingView
    }

    public void addLobbyFirebase(String lobbyNaam) {
        System.out.println(lobbyNaam);
        Button btn = new Button(lobbyNaam);
        panel.add(new Button(lobbyNaam), 0, gridCounter);
        System.out.println("test" + lobbyNaam);
    }

    public void hostGame(ActionEvent t) {
        if (gridCounter < 5) {
            gridCounter++;
            join();
        } else {
            ((Node) t.getSource()).setOpacity(0);
            root.getChildren().remove(t.getSource());
            System.out.println("Max aantal lobbies");
        }
    }

    public String getLobbyNaam() {
        return lobbyCon.getLobbyNaam();
    }

    @Override
    public void update(LobbyObservable lo) {
        List<String> lobbynamen =  lo.getLobbyName();
        System.out.println(lobbynamen.size());
        for(String test:lobbynamen){
            System.out.println(test);
            addLobbyFirebase(test);
        }
    }
}
