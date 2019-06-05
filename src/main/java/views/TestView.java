package views;

import controlers.TestController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TestView {
    Scene scene;
    TestController testCon;

    public TestView(TestController testCon){
        this.testCon = testCon;
        loadScene();
    }
    private void loadScene(){
        GridPane gPane = new GridPane();
        Button button = new Button("TestSCENE!!!");
//        button.setOnAction(e-> changeSceneToArea());
        gPane.add(button, 0, 0);
        scene = new Scene(gPane, 400, 400);
    }

}
