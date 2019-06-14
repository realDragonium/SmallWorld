package views;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import observable.DiceObservable;
import observers.DiceObserver;

import java.util.ArrayList;

import controlers.DiceController;


public class DiceView implements DiceObserver {
	
	GridPane root;

    ImageView showImageView = new ImageView("/Dice/DiceTwo.jpg");
    Image One = new Image("/Dice/DiceOne.jpg");
    Image Two = new Image("/Dice/DiceTwo.jpg");
    Image Three = new Image("/Dice/DiceThree.jpg");
    Image Four = new Image("/Dice/DiceFour.jpg");
    Image Five = new Image("/Dice/DiceFive.jpg");
    Image Six = new Image("/Dice/DiceSix.jpg");

    Stage primaryStage = new Stage();
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(showImageView.imageProperty(), One)),
            new KeyFrame(Duration.millis(1000), new KeyValue(showImageView.imageProperty(), Two)),
            new KeyFrame(Duration.millis(1500), new KeyValue(showImageView.imageProperty(), Three)),
            new KeyFrame(Duration.millis(2000), new KeyValue(showImageView.imageProperty(), Four)),
            new KeyFrame(Duration.millis(2500), new KeyValue(showImageView.imageProperty(), Five)),
            new KeyFrame(Duration.millis(3000), new KeyValue(showImageView.imageProperty(), Six))
    );

    DiceController diceController;
    ArrayList<Image> list = new ArrayList<>();
    int counter = 0;


    public DiceView(DiceController diceController) {
    	this.diceController = diceController;
        diceController.registreer(this);
        loadScene();
    }

    public void loadScene() {

        Button dice = new Button("RollDice");


        list.add(new Image("/Dice/DiceOne.jpg"));
        list.add(new Image("/Dice/DiceTwo.jpg"));
        list.add(new Image("/Dice/DiceThree.jpg"));
        list.add(new Image("/Dice/DiceFour.jpg"));
        list.add(new Image("/Dice/DiceFive.jpg"));
        list.add(new Image("/Dice/DiceSix.jpg"));
        dice.setOnAction(e -> playAnimation());
        root = new GridPane();
        root.add(dice, 0, 0);
        root.add(showImageView, 0, 1);
    }

    public void playAnimation() {
//
//        for (counter = 0; counter < list.size(); counter++) {
////            showImageView.setImage(list.get((int) (Math.random() * 6)));
//
////            showImageView.setImage(list.get((int) (Math.random() * 6)));
////		showImageView  = new ImageView("/images/DiceTwo.jpg");
//
//
//        }
        timeline.play();
        timeline.setOnFinished(e -> diceController.ClickedDice());
    }

    @Override
    public void update(DiceObservable ob) {
        System.out.println("De waarde is" + (ob.getWaarde()));

        showImageView.setImage(list.get(ob.getWaarde() -1));
    }

	public void setPane(Pane pane) {
		pane.getChildren().add(root);
	}
}
