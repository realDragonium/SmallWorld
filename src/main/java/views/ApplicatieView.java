package views;

import controlers.ApplicatieController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.ApplicatieObserable;
import observers.ApplicatieObserver;


public class ApplicatieView implements ApplicatieObserver {

    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Stage primaryStage;
    ApplicatieController applicatieController;
    GridPane gPane = new GridPane();


    public ApplicatieView(Stage s){
        primaryStage = s;
        applicatieController = new ApplicatieController();
        applicatieController.registerObserver(this);
        Button button = new Button("switch");
        gPane.add(button, 0, 0);
        loadPrimaryStageWithGridPane(gPane);
    }

    private void loadPrimaryStageWithGridPane(GridPane gp) {
        try {
            GridPane root = gp;
            Scene scene = new Scene(root,width,height);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ApplicatieObserable ao) {
        primaryStage.setScene(ao.getScene());
    }
}