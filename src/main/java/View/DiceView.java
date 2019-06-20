package View;

import Controller.DiceController;
import Managers.SceneManager;
import Observer.DiceObserver;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Observable.DiceObservable;

import java.util.ArrayList;


public class DiceView implements DiceObserver {


    Group root;
    DiceController diceController;

    ImageView showImageView = new ImageView(getClass().getResource("/Dice/DiceNul.jpg").toExternalForm());
    Image One = new Image("/Dice/DiceNul.jpg");
    Image Two = new Image("/Dice/DiceNul.jpg");
    Image Three = new Image("/Dice/DiceNul.jpg");
    Image Four = new Image("/Dice/DiceOne.jpg");
    Image Five = new Image("/Dice/DiceTwo.jpg");
    Image Six = new Image("/Dice/DiceThree.jpg");

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(showImageView.imageProperty(), One)),
            new KeyFrame(Duration.millis(1000), new KeyValue(showImageView.imageProperty(), Four)),
            new KeyFrame(Duration.millis(1500), new KeyValue(showImageView.imageProperty(), Three)),
            new KeyFrame(Duration.millis(2000), new KeyValue(showImageView.imageProperty(), Six)),
            new KeyFrame(Duration.millis(2500), new KeyValue(showImageView.imageProperty(), Two)),
            new KeyFrame(Duration.millis(3000), new KeyValue(showImageView.imageProperty(), Five))
    );


    ArrayList<Image> list = new ArrayList<>();
    int counter = 0;


    public DiceView(DiceController diceCon, Group group) {
        this.diceController = diceCon;
        this.root = group;

        diceController.registreer(this);
        createScene();
    }

    public void createScene() {
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceNul.jpg"));
        list.add(new Image("/Dice/DiceOne.jpg"));
        list.add(new Image("/Dice/DiceTwo.jpg"));
        list.add(new Image("/Dice/DiceThree.jpg"));

        GridPane gridPane = new GridPane();
        gridPane.add(showImageView, 0, 1);


        root.getChildren().add(gridPane);
        gridPane.setTranslateX(550);
        gridPane.setTranslateY(720);
    }

    public void playAnimation(int waarde) {

        timeline.play();
        timeline.setOnFinished(e -> {
            showImageView.setImage(list.get(waarde));
        });


    }


    @Override
    public void update(DiceObservable ob) {
        if(ob.isPlaying()){
            playAnimation(ob.getWaarde());
        }
    }
}


