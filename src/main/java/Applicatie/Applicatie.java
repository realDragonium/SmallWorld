package Applicatie;

import Controller.AccountController;
import Controller.LoginController;
import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applicatie {
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
    private Stage primaryStage;
    private Group root = new Group();
    private FirebaseServiceOwn fb;
    private AccountController accountCon;

    public Applicatie(Stage primaryStage) {
        SceneManager.getInstance().registerApp(this);
        this.primaryStage = primaryStage;
        loadPrimaryStage();
    }

    public void setAccount(AccountController accountCon){
        this.accountCon = accountCon;
    }

    public FirebaseServiceOwn getFirebaseService(){
        if(fb == null) fb = new FirebaseServiceOwn();
        return fb;
    }

    public AccountController getAccountCon(){
        return accountCon;
    }

    private void loadPrimaryStage() {
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Small World");
//        primaryStage.setX(windowAnchorX);
//        primaryStage.setY(windowAnchorY);
//        primaryStage.setFullScreen(true);
        primaryStage.show();

       new LoginController();
       /// new LobbyController();
    }

    public void changeScene(Scene scene) {
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
    }
}

