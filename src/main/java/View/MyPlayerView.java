package View;

import Controller.MyPlayerController;
import Observable.PlayerObservable;
import Observer.PlayerObserver;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MyPlayerView implements PlayerObserver{

    Group group;
    MyPlayerController mpCon;

    public Pane pane;
    public Text playerId;
    public Text punten;
    public Text activeRace;
    public Text activePower;
    public Button showActive;

    public MyPlayerView(Group group, MyPlayerController mpCon){
        this.group = group;
        this.mpCon = mpCon;
        mpCon.registerAtMyPlayer(this);
    }

    public void initialize(){
        group.getChildren().add(pane);
    }

    public void showActive(){
        mpCon.showMyActiveAreas();
        if(showActive.getText().equals("show areas")){
            showActive.setText("hide areas");
        }
        else{
            showActive.setText("show areas");
        }
    }

    @Override
    public void update(PlayerObservable po) {
        punten.setText(Integer.toString(po.getPunten()));
        playerId.setText(po.getId());
        if(po.getActive() != null) {
            activeRace.setText(po.getActive().getRace().getId());
            activePower.setText(po.getActive().getPower().getId());
        }
    }
}
