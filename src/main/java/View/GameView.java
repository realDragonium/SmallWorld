package View;

import Controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class GameView {



    @FXML
    public Group mapGroup;
    @FXML
    public Group buttonGroup;
    @FXML
    public Group playerGroup;
    @FXML
    public Group roundGroup;
    @FXML
    public Group turnGroup;


    private GameController gameCon;
    private AnchorPane root;

    private String text;
    private Pane game;

    public void setPane(Pane pane) {
        this.game = pane;
    }
    public void addPane(Pane component) {
        game.getChildren().add(component);
    }

    public GameView(GameController gameCon) {
        this.gameCon = gameCon;
    }

    public void initialize() {
//        new Map2DView(mapGroup);
//        loadButtons();
//        loadPlayer("player0");
//        loadPlayer("player1");
//        loadPlayer("player2");
//        loadPlayer("player3");
//        loadPlayer("player4");
//        loadRound();
//        loadTurn();
    }


}
