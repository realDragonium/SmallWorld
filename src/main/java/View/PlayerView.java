package View;

import Controller.PlayerController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PlayerView implements PlayerObserver {

    private String id;
    private Group group;
    private PlayerController playerCon;

    @FXML
    public Pane pane;
    @FXML
    public TextField playerId;
    @FXML
    public TextField fiches;
    @FXML
    public TextField punten;

    public PlayerView(String id, Group group, PlayerController playerCon){
        this.playerCon = playerCon;
        this.id = id;
        this.group = group;
    }

    public void initialize() {
        group.getChildren().add(pane);
        playerId.setText(id);
        playerCon.register(this);
        playerCon.makeNewRace();
        pane.setLayoutY( 110 + (150*Integer.parseInt(id.split("yer")[1])));
    }

    @Override
    public void update(PlayerObservable po) {
        updateFields(po.getFiches(), po.getPunten());
    }

    private void updateFields(int fiches, int punten){
        this.fiches.setText("fiches: " + fiches);
        this.punten.setText("punten: " + punten);
    }
}
