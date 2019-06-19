package Applicatie;

import Controller.FirebaseController;
import Controller.LoginController;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Applicatie {
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
    private Stage primaryStage;
    private Pane root = new Pane();
    private static FirebaseServiceOwn fb;

    public Applicatie(Stage primaryStage) {
        SceneManager.getInstance().registerApp(this);
        this.primaryStage = primaryStage;
        loadPrimaryStage();
    }

    public static FirebaseServiceOwn getFirebaseService(){
        if(fb == null) fb = new FirebaseServiceOwn();
        return fb;
    }

    private void loadPrimaryStage() {
        Scene scene = new Scene(root, width,height);
        SceneManager.getInstance().setPane(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Small World");
//        primaryStage.setX(windowAnchorX);
//        primaryStage.setY(windowAnchorY);
//        primaryStage.setFullScreen(true);
        primaryStage.show();

        new FirebaseController();

        new LoginController();
    }

    public void loadScene(Scene scene) {
        primaryStage.setScene(scene);

        SceneManager.getInstance().setPane(root);
    }
}

