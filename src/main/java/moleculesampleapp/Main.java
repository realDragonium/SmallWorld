package moleculesampleapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import moleculesampleapp.Xform;

import java.io.IOException;


/**
 * @author cmcastil
 */
public class Main extends Application {

    private Xform World;
    private Animation anim1;

    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform moleculeGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -150;
    private static final double CAMERA_INITIAL_X_ANGLE = -90.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private static final double HYDROGEN_ANGLE = 104.5;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    //   private void buildScene() {
    //       root.getChildren().add(world);
    //   }
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);

    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(false);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;

                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
                    camera.setTranslateZ(newZ);
                } else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
                }
            }
        });
    }

    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;

                    case A:
                        anim1.playAnimation(World, false);
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        World = new Xform();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/map2.fxml"));
        Group graphic = fxmlLoader.load();
        World.getChildren().add(graphic);

        anim1 = new Animation();
        anim1.addAnimationPoint(new Translate(0, 105, 0), 0, 0, 0, 5);
        anim1.addAnimationPoint(new Translate(0, 95, 0), 0, 0, 45, 6);
        anim1.addAnimationPoint(new Translate(0, 105, 0), 0, 0, 90, 7);
        anim1.addAnimationPoint(new Translate(0, 95, 0), 0, 0, 90, 9);
        anim1.addAnimationPoint(new Translate(0, 105, 0), 0, 0, 90, 10.0);
        anim1.addAnimationPoint(new Translate(0, 100, 0), 0, 0, 90, 11.0);
        anim1.addAnimationPoint(new Translate(-30, 100, 0), 0, 0, 90, 12.0);
        anim1.addAnimationPoint(new Translate(1000, 100, 0), 0, 0, 90, 13.0);

        for (Node mesh : graphic.getChildren()) {

            mesh.setOnMouseClicked(e -> {
                System.out.println(mesh.getId());
            });

            mesh.setOnMouseEntered(e -> {
                PhongMaterial curMaterial = (PhongMaterial) ((Shape3D) e.getSource()).getMaterial();
                curMaterial.setDiffuseColor(curMaterial.getDiffuseColor().brighter());
            });

            mesh.setOnMouseExited(e -> {
                PhongMaterial curMaterial = (PhongMaterial) ((Shape3D) e.getSource()).getMaterial();
                curMaterial.setDiffuseColor(curMaterial.getDiffuseColor().darker());
            });
        }
//	        root.getChildren().add(world);
//	        root.setDepthTest(DepthTest.ENABLE);
        world.getChildren().add(World);


        //buildScene();
        buildCamera();
        //buildAxes();
        //buildMolecule();

        //graphic.;


        SubScene subScene = new SubScene(world, 1920, 1080, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        subScene.setDepthTest(DepthTest.ENABLE);

        Group group = new Group();
//	        TextField textFieldRotateX = new TextField();
//	        TextField textFieldRotateY = new TextField();
//	        TextField textFieldRotateZ = new TextField();
//
//	        group.getChildren().addAll(textFieldRotateX, textFieldRotateY, textFieldRotateZ);

        Button button = new Button("Hello");
        group.getChildren().add(button);

        root.getChildren().addAll(subScene, group);

        Scene scene = new Scene(root, 1920, 1080, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene, world);
        handleMouse(scene, world);

        primaryStage.setTitle("Game View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void run(String[] args) {
        launch(args);
    }

    
}

