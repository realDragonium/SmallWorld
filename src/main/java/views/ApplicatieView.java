package views;

import controlers.ApplicatieController;
import observable.ModelViewObservable;
import observable.SceneObservable;
import observers.ModelViewObserver;
import observers.SceneObserver;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ApplicatieView implements ModelViewObserver, SceneObserver{
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
	private Stage primaryStage;
	private ApplicatieController appCon = ApplicatieController.getInstance();
	
	public ApplicatieView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		appCon.registerObserver(this);
		loadPrimaryStage();
	}
	
	private void loadPrimaryStage() {
        try {
            GridPane root = new GridPane();
            Button button = new Button("Go to HomeScreen");
            button.setOnAction(e -> {
            	System.out.println("button pressed!");
            	new HomeScreenView(this);
            });
            root.add(button, 0, 0);
            Scene scene = new Scene(root,width,height);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Small World");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	private void changeScene(Scene scene) {
		primaryStage.setScene(scene);
	}

	@Override
	public void update(ModelViewObservable mvo) {
		System.out.println("update mvo applicatie test");
	}

	@Override
	public void update(SceneObservable so) {
		System.out.println("update so applicatie test");
		changeScene(so.getSceneSO());
	}
	
	
	
}
