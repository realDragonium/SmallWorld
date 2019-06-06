package views;

import Observable.DepthObservable;
import Observable.GameObservable;
import Observer.DepthObserver;
import Observer.GameObserver;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import moleculesampleapp.Xform;

public class DepthView implements DepthObserver, GameObservable{
    private GameObserver gameObserver;
    private SubScene world;
    public Xform World = new Xform();

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private final double CAMERA_INITIAL_DISTANCE = -150;
    private final double CAMERA_INITIAL_X_ANGLE = -90.0;
    private final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private final double CAMERA_NEAR_CLIP = 0.1;
    private final double CAMERA_FAR_CLIP = 10000.0;

    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    public DepthView(GameObserver go) {
        register(go);
        buildCamera();
        generateWorld();
    }

    public void addToWorld(Node item){
        World.getChildren().add(item);
    }

    private void generateWorld(){
        new MapView(this);
    }

    private void addGroup(Group group) {
        World.getChildren().add(group);
        remakeSubScene();
        notifyObserver();
    }

    public void remakeSubScene(){
        world = new SubScene(World, 1600, 900, true, SceneAntialiasing.BALANCED);
        world.setCamera(camera);
        world.setDepthTest(DepthTest.ENABLE);
        handleMouse(world, World);
    }

    private void buildCamera() {
        World.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.addRotate(0,0,180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    @Override
    public void update(DepthObservable depthO) {
        addGroup(depthO.getGroup());
    }

    @Override
    public void notifyObserver() {
        gameObserver.update(this);
    }

    @Override
    public void register(GameObserver go) {
        gameObserver = go;
    }

    @Override
    public SubScene getSubScene() {
        return world;
    }

    private void handleMouse(SubScene scene, final Node root) {
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
}
