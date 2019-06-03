package moleculesampleapp;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

public class Animation implements Cloneable {

    private float playRate = 0.1f;
    private ArrayList<AnimPoint> animationPoints = new ArrayList<AnimPoint>();
    private Translate objectBeginTranslate;
    private AnimPoint nextPoint = null;
    private AnimPoint lastPoint;
    private Translate deltaPosition;
    private double deltaRotationX;
    private double deltaRotationY;
    private double deltaRotationZ;
    private double animFrame = 0;
    private Xform model;
    public boolean playing;

    public Animation() {
        animationPoints.add(new AnimPoint(new Translate(0, 0, 0), new Rotate(0), new Rotate(0), new Rotate(0), 0.0));
    }

    public void addAnimationPoint(Translate pos, double rotX, double rotY, double rotZ, double time) {
        animationPoints.add(new AnimPoint(pos, new Rotate(rotX), new Rotate(rotY), new Rotate(rotZ), time));
    }
    
    public void setAnimatedObject(Xform model) {
    	this.model = model;
    	objectBeginTranslate = new Translate(model.t.getX(), model.t.getY(), model.t.getZ());
    	
    }

    public void playAnimation(boolean loop) {
        
        if (loop) {

        } else {

            if (nextPoint == null) {
                findNextAnimPoint(model);
            } else if (animFrame >= nextPoint.time) {
                findNextAnimPoint(model);
            }
            if(lastPoint.time == nextPoint.time) {

            	AnimationController.animations.remove(this);
            }
            else {
            	nextAnimFrame(model);
            }
            animFrame += playRate;
        }
    }

    public void nextAnimFrame(Xform model) {
        model.t.setX(model.t.getX() + deltaPosition.getX());
        model.t.setY(model.t.getY() + deltaPosition.getY());
        model.t.setZ(model.t.getZ() + deltaPosition.getZ());
        model.rx.setAngle(model.rx.getAngle() + deltaRotationX);
        model.ry.setAngle(model.ry.getAngle() + deltaRotationY);
        model.rz.setAngle(model.rz.getAngle() + deltaRotationZ);
    }

    public Translate getPosChange() {
        double animTime = nextPoint.time - lastPoint.time;
        double deltaX = nextPoint.translate.getX() - lastPoint.translate.getX();
        double deltaY = nextPoint.translate.getY() - lastPoint.translate.getY();
        double deltaZ = nextPoint.translate.getZ() - lastPoint.translate.getZ();
        double positionStepX = deltaX / (animTime / playRate);
        double positionStepY = deltaY / (animTime / playRate);
        double positionStepZ = deltaZ / (animTime / playRate);
        return new Translate(positionStepX, positionStepY, positionStepZ);

    }

    public void confRotChange() {
        double animTime = nextPoint.time - lastPoint.time;
        double deltaX = nextPoint.rotateX.getAngle() - lastPoint.rotateX.getAngle();
        double deltaY = nextPoint.rotateY.getAngle() - lastPoint.rotateY.getAngle();
        double deltaZ = nextPoint.rotateZ.getAngle() - lastPoint.rotateZ.getAngle();
        deltaRotationX = deltaX / (animTime / playRate);
        deltaRotationY = deltaY / (animTime / playRate);
        deltaRotationZ = deltaZ / (animTime / playRate);
    }

    public void findNextAnimPoint(Xform model) {
        lastPoint = nextPoint;
        double time = 1000;
        if (nextPoint == null) {
            lastPoint = animationPoints.get(0);
            nextPoint = animationPoints.get(1);
            time = nextPoint.time;
        }
        for (int i = 2; i < animationPoints.size(); i++) {
            if (animationPoints.get(i).time < time && animationPoints.get(i).time > lastPoint.time) {
                nextPoint = animationPoints.get(i);
                time = nextPoint.time;
            }
        }
        deltaPosition = getPosChange();
        confRotChange();
    }
    
    public Object clone() throws CloneNotSupportedException 
    { 
    	return super.clone(); 
    } 
}
