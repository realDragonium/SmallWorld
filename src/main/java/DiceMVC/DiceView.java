package DiceMVC;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class DiceView implements Observer {

    ImageView showImageView = new ImageView("/images/DiceTwo.jpg");
    Image One = new Image("/images/DiceOne.jpg");
    Image Two = new Image("/images/DiceTwo.jpg");
    Image Three = new Image("/images/DiceThree.jpg");
    Image Four = new Image("/images/DiceFour.jpg");
    Image Five = new Image("/images/DiceFive.jpg");
    Image Six = new Image("/images/DiceSix.jpg");

    Stage primaryStage = new Stage();
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(showImageView.imageProperty(), One)),
            new KeyFrame(Duration.millis(1000), new KeyValue(showImageView.imageProperty(), Two)),
            new KeyFrame(Duration.millis(1500), new KeyValue(showImageView.imageProperty(), Three)),
            new KeyFrame(Duration.millis(2000), new KeyValue(showImageView.imageProperty(), Four)),
            new KeyFrame(Duration.millis(2500), new KeyValue(showImageView.imageProperty(), Five)),
            new KeyFrame(Duration.millis(3000), new KeyValue(showImageView.imageProperty(), Six))
    );


    DiceController diceController = new DiceController();
    ArrayList<Image> list = new ArrayList<>();
    int counter = 0;


    public DiceView(Stage primaryStage) {
        diceController.registreer(this);
        this.primaryStage = primaryStage;
        createScene();
    }

    public void createScene() {

        Button dice = new Button("RollDice");


        list.add(new Image("/images/DiceOne.jpg"));
        list.add(new Image("/images/DiceTwo.jpg"));
        list.add(new Image("/images/DiceThree.jpg"));
        list.add(new Image("/images/DiceFour.jpg"));
        list.add(new Image("/images/DiceFive.jpg"));
        list.add(new Image("/images/DiceSix.jpg"));


        dice.setOnAction(e -> playAnimation());


        GridPane gridPane = new GridPane();
        gridPane.add(dice, 0, 0);
        gridPane.add(showImageView, 0, 1);


        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("test");
        primaryStage.show();

    }

    public void playAnimation() {

        for (counter = 0; counter < list.size(); counter++) {
//            showImageView.setImage(list.get((int) (Math.random() * 6)));

//            showImageView.setImage(list.get((int) (Math.random() * 6)));
//		showImageView  = new ImageView("/images/DiceTwo.jpg");


        }
        timeline.play();
        timeline.setOnFinished(e -> diceController.ClickedDice());


    }


    @Override
    public void update(Observable ob) {
        System.out.println("De waarde is" + ob.getWaarde());

        showImageView.setImage(list.get(ob.getWaarde()));


    }
}
