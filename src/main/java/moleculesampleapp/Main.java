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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlers.AnimationControler;
import controlers.AnimationsControler;
import views.AreaView;


/**
 * @author cmcastil
 */
public class Main extends Application {

    public static Xform World;

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
    AnimationControler CoinFalling;
    Map<String, Translate> AreaPoints;

    List<Translate> centerPoints = new ArrayList<Translate>();

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    AnimationsControler animControler = new AnimationsControler();

    //   private void buildScene() {
    //       root.getChildren().add(world);
    //   }
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
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

    private void generateWorld() throws IOException {
    	 World = new Xform();
         FXMLLoader fxmlLoader = new FXMLLoader();
         fxmlLoader.setLocation(this.getClass().getResource("/map2.fxml"));
         Group graphic = fxmlLoader.load();
         World.getChildren().add(graphic);

         AreaPoints = new HashMap<String, Translate>();
         generateRegionPoints();
         for (Node mesh : graphic.getChildren()) {

         	mesh.setId(mesh.getId().substring(14, mesh.getId().length()));
         	if(mesh.getId().length() > 6) {

//         		AreaView areaView = new AreaView(((Shape3D) mesh), AreaPoints.get(mesh.getId()), );
//         		areaView.controler.setAnimationsControler(animControler);
         	}
         }
         world.getChildren().add(World);
    }

    private void generateRegionPoints() {
    	AreaPoints.put("Region_swamp_1", new Translate(-4.156599415245687,0.2828154572177475,-3.92273013556655));
        AreaPoints.put("Region_field_000", new Translate(-4.377606657290242,0.2195236338358768,-2.4536997720124774));
        AreaPoints.put("Region_forest_002", new Translate(-4.3756214487168865,-0.014626000076532364,-0.9650110392113624));
        AreaPoints.put("Region_swamp_9", new Translate(-3.196912626638619,-0.014626000076532364,-1.3158436857848215));
        AreaPoints.put("Region_forest_001", new Translate(-3.194002179866176,-0.014626000076528811,-2.9480634079940873));
        AreaPoints.put("Region_forest_000", new Translate(-2.623162493856621,-0.014626000076528811,-4.251121461313619));
        AreaPoints.put("Region_hill_000", new Translate(-1.1804655577884957,-0.014626000076532364,-4.166059893291845));
        AreaPoints.put("Region_field_001", new Translate(-1.6241406155040463,-0.014626000076525258,-3.158264082605439));
        AreaPoints.put("Region_hill_007", new Translate(-1.7351651591055661,-0.2644657268755992,-1.7339541835205292));
        AreaPoints.put("Region_field_008", new Translate(-2.227804703262793,-0.014626000076528811,-0.31508143488692164));
        AreaPoints.put("Region_forest_008", new Translate(-2.595382224729802,-0.014626000076528811,0.7297700547102399));
        AreaPoints.put("Region_hill_008", new Translate(-3.697993510559527,-0.014626000076535917,1.1176583801944544));
        AreaPoints.put("Region_swamp_4", new Translate(-1.280747956449497,-0.014626000076528811,0.6001833426680969));
        AreaPoints.put("Region_mountain_008", new Translate(-1.1011609156349642,-1.0191613281066907,-0.8602427975542593));
        AreaPoints.put("Region_hill_005", new Translate(-1.796276158826224,-0.014626000076528811,1.8692600344371195));
        AreaPoints.put("Region_swamp_10", new Translate(-2.457673813965936,-0.014626000076532364,3.136527532792715));
        AreaPoints.put("Region_hill_006", new Translate(-0.1091808583052363,-0.014626000076532364,0.41754336015345855));
        AreaPoints.put("Region_field_006", new Translate(-0.2647353973216672,-0.014626000076528811,1.720696463192692));
        AreaPoints.put("Region_field_007", new Translate(-0.9377912027160552,-0.014626000076535917,2.937985008436038));
        AreaPoints.put("Region_mountain_001", new Translate(-2.9310178085161738,-0.8517015207605851,4.162787943457596));
        AreaPoints.put("Region_swamp_5", new Translate(-1.4326999439799806,-0.014626000076532364,4.3507779453520286));
        AreaPoints.put("Region_mountain_000", new Translate(-0.3254747240145929,-0.8629614977149096,4.205362280703967));
        AreaPoints.put("Region_hill_004", new Translate(0.2905918450238234,-0.014626000076528811,3.0281788566347436));
        AreaPoints.put("Region_mountain_009", new Translate(0.9481959908146632,-0.8529943463827045,1.9650840868562625));
        AreaPoints.put("Region_mountain_002", new Translate(1.8384431138211594,-0.9087347335170328,3.027493546033427));
        AreaPoints.put("Region_forest_007", new Translate(0.8541097044096265,-0.014626000076528811,4.287564153152981));
        AreaPoints.put("Region_field_005", new Translate(2.4141953685080346,-0.014626000076532364,4.228055915233228));
        AreaPoints.put("Region_field_004", new Translate(3.4826963818883874,-0.014626000076535917,3.5358286310107188));
        AreaPoints.put("Region_hill_003", new Translate(4.024492684143276,-0.014626000076528811,2.1027593038960912));
        AreaPoints.put("Region_mountain_003", new Translate(3.367248760962158,-0.7329777303065548,0.9172767026881242));
        AreaPoints.put("Region_swamp_7", new Translate(2.5466409524349856,-0.01462780056944979,2.0267702184965777));
        AreaPoints.put("Region_forest_003", new Translate(1.9478916296532847,-0.014626000076525258,1.146821793516988));
        AreaPoints.put("Region_swamp_3", new Translate(2.1757160496535417,-0.014626000076532364,-0.062047869699140515));
        AreaPoints.put("Region_field_003", new Translate(3.868008906088377,-0.014626000076528811,0.020840405451418093));
        AreaPoints.put("Region_swamp_8", new Translate(4.4708141441445335,-0.014626000076535917,-1.2037288124246848));
        AreaPoints.put("Region_hill_002", new Translate(3.96745867663354,-0.014626000076532364,-2.4598241282227757));
        AreaPoints.put("Region_hill_001", new Translate(3.091535034243995,-0.014626000076535917,-1.228467162126804));
        AreaPoints.put("Region_forest_004", new Translate(2.0926507813397426,-0.014626000076532364,-1.5003234489745805));
        AreaPoints.put("Region_field_002", new Translate(1.0843275566423654,-0.014626000076525258,-2.173368807845562));
        AreaPoints.put("Region_swamp_2", new Translate(-0.13968312276631448,0.22374710768452744,-3.3736655683818206));
        AreaPoints.put("Region_mountain_007", new Translate(-0.05551844204616504,-0.6599615763618019,-4.4413418400179845));
        AreaPoints.put("Region_forest_006", new Translate(0.7702547490826848,0.1873131339208598,-3.7156081482296));
        AreaPoints.put("Region_mountain_005", new Translate(2.0355183940917003,-0.6167276684607224,-3.906709729811618));
        AreaPoints.put("Region_mountain_004", new Translate(2.455748322931142,-0.6374180640768472,-2.890114055821917));
        AreaPoints.put("Region_forest_005", new Translate(3.5281943388643886,-0.014626000076532364,-3.9610249147401517));
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
                    	animControler.updateAnimations();
                }
            }
        });
    }

    public void createFallingAnimation() {
		AnimationControler CoinFalling = animControler.createAnimation("Coin Falling");
        CoinFalling.createAnimPoint(new Translate(0,-15,0), 0, 0, 0, 1);
        CoinFalling.createAnimPoint(new Translate(0,0,0), 0, 0, 0, 70);
	}

    @Override
    public void start(Stage primaryStage) throws IOException {
    	createFallingAnimation();

        generateWorld();

        buildCamera();
        SubScene subScene = new SubScene(world, 1920, 1080, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        subScene.setDepthTest(DepthTest.ENABLE);

        Group group = new Group();
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

    public static void run(String[] args) {
        launch(args);
    }


}
