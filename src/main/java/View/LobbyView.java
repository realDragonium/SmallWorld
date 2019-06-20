package View;

import Controller.HomeScreenController;
import Controller.LobbyController;
import Observer.LobbyObserver;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import Observable.ObservableLobby;
import java.util.List;

// Get the single object for the SpelbordController
// U
// Elke class hoort een stukje javadoc te hebben

/** This View-class is part of the MVC design pattern and shows the lobby screen (available lobbies).
 * @author Lars Puente Blom
 * @version Juni 2019
 *
 *
 *
 *
 */

public class LobbyView implements LobbyObserver {
    LobbyController lobbyCon;
    private Group group;
    public Group root;
    public Button terug, hosten;
    public GridPane panel;
    private Button activeButton;
    private int gridCounter = 0;             // startwaarde aantal aangemaakte lobbies = 0
    private GridPane grid= new GridPane();

    /**
     * @param group is given argument for the scene manager
     * @param con is given argument to the controller so that its mvc.
     */

    public LobbyView(Group group, LobbyController con) {
        this.group = group;
        this.lobbyCon = con;
    }

    // Is called after all @FXML are injected (in this case the public defined attributes)
    public void initialize() {
        group.getChildren().add(root);
        lobbyCon.register(this);
        panel.setVgap(10);
        List<String> lijst = lobbyCon.getFirebaseLobbyNamen();   // Gets lobbynames from firebase and puts in a list

        for(String lobbyNaam: lijst) {
            Button btn = new Button(lobbyNaam);
            panel.add(btn, 0, gridCounter);
            panel.getChildren().get(gridCounter).setId("button" + gridCounter);
            gridCounter++;

            // On button clicked, the button will change red.
            btn.setOnAction(d -> {
                if (activeButton != null) {
                    activeButton.setStyle(" -fx-background-color:   -fx-background");
                }
                btn.setStyle("-fx-background-color: red;");
                activeButton = btn;
            });
        }
    }

    /** The method terug creates a new HomeScreenController which gets loaded by the Scenemanager.
     */
    public void terug() {
        new HomeScreenController();
    }

    /** The method join
     */
    public void join() {
        lobbyCon.joinLobby(activeButton.getText());               // start de LobbySettingView
    }

    /** The method host is
     */
    public void host(){
        lobbyCon.lobbyEdit();
    }

    /** The method addLobbyFirebase adds a new button to a gridPane
     * @param lobbyNaam gives the lobbynaam as parameter
     */

    public void addLobbyFirebase(String lobbyNaam) {
        panel.add(new Button(lobbyNaam), 0, gridCounter);
    }

    public void hostGame(ActionEvent t) {
        if (gridCounter < 5) {
            gridCounter++;
            host();
        } else {
            ((Node) t.getSource()).setOpacity(0);
            root.getChildren().remove(t.getSource());
            System.out.println("Max aantal lobbies");
        }
    }


    /** The view gets the update from the ObservableLobby.
     * @param lo gets send along to get
     */
    @Override
    public void update(ObservableLobby lo) {
        List<String> lobbynamen =  lo.getLobbyName();
        System.out.println(lobbynamen.size());
        for(String test:lobbynamen){
            System.out.println(test);
            addLobbyFirebase(test);
        }
    }
}
