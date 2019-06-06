package controlers;

import javafx.scene.transform.Translate;
import models.AnimationModel;
import moleculesampleapp.Xform;

public class AnimationControler{
	AnimationModel model;
	AnimationsControler animsControler;
	
	
	public AnimationControler(String animName) {
		model = new AnimationModel();
		model.name = animName;
		createAnimPoint(new Translate(0,0,0) ,0,0,0,0);
	}
    
    public AnimationControler() {
    	model = new AnimationModel();
    	createAnimPoint(new Translate(0,0,0) ,0,0,0,0);
	}

	public void createAnimPoint(Translate pos, double rotX, double rotY, double rotZ, int time) {
    	AnimPointControler point = new AnimPointControler();
    	point.setTranslate(pos);
    	point.setRotation(rotX, rotY, rotZ);
    	point.setTime(time);
    	model.addAnimPoint(point);
    }
    
    public String getName() {
    	return model.name;
    }
    
    public void setAnimatedObject(Xform animObject) {
    	model.animObject = animObject;
    	model.objectBeginTranslate = animObject.t;
    	
    }

    public void confPosChange() {
    	AnimPointControler nextPoint = model.AnimPoints.get(model.nextAnimPoint);
    	AnimPointControler lastPoint = model.AnimPoints.get(model.nextAnimPoint - 1);
        double animTime = nextPoint.model.time - lastPoint.model.time;
        double deltaX = nextPoint.model.translate.getX() - lastPoint.model.translate.getX();
        double deltaY = nextPoint.model.translate.getY() - lastPoint.model.translate.getY();
        double deltaZ = nextPoint.model.translate.getZ() - lastPoint.model.translate.getZ();
        double positionStepX = deltaX / animTime;
        double positionStepY = deltaY / animTime;
        double positionStepZ = deltaZ / animTime;
        model.deltaPosition =  new Translate(positionStepX, positionStepY, positionStepZ);

    }

    public void confRotChange() {
    	AnimPointControler nextPoint = model.AnimPoints.get(model.nextAnimPoint);
    	AnimPointControler lastPoint = model.AnimPoints.get(model.nextAnimPoint - 1);
        double animTime = nextPoint.model.time - lastPoint.model.time;
        double deltaX = nextPoint.model.rotateX.getAngle() - lastPoint.model.rotateX.getAngle();
        double deltaY = nextPoint.model.rotateY.getAngle() - lastPoint.model.rotateY.getAngle();
        double deltaZ = nextPoint.model.rotateZ.getAngle() - lastPoint.model.rotateZ.getAngle();
        model.deltaRotationX = deltaX / animTime;
        model.deltaRotationY = deltaY / animTime;
        model.deltaRotationZ = deltaZ / animTime; 
    }
    
    public Xform getObject() {
    	return model.animObject;
    }
    
    public void nextFrame() {
    	//System.out.println(model.animObject.t);
    	System.out.println(model.animObject);
    	System.out.println(model.deltaPosition);
    	
    	
    	model.animObject.t.setX(model.animObject.t.getX() + model.deltaPosition.getX());
        model.animObject.t.setY(model.animObject.t.getY() + model.deltaPosition.getY());
        model.animObject.t.setZ(model.animObject.t.getZ() + model.deltaPosition.getZ());
        model.animObject.addTranslate(model.deltaPosition);
        
        model.animObject.rx.setAngle(model.animObject.rx.getAngle() + model.deltaRotationX);
        model.animObject.ry.setAngle(model.animObject.ry.getAngle() + model.deltaRotationY);
        model.animObject.rz.setAngle(model.animObject.rz.getAngle() + model.deltaRotationZ);
        
    	model.curFrame++;
    	if(model.AnimPoints.get(model.nextAnimPoint).model.time == model.curFrame && model.nextAnimPoint < model.AnimPoints.size() - 1) {
    		model.nextAnimPoint++;
    		confRotChange();
    		confPosChange();
    	}
    	System.out.println(model.nextAnimPoint);
    	if(model.nextAnimPoint == model.AnimPoints.size() - 1 && model.AnimPoints.get(model.nextAnimPoint).model.time == model.curFrame) {
    		System.out.println("ja");
    		animsControler.stopAnimation(this);
    		//resetAnimation();
    	}
    }

	public void resetAnimation() {
		model.curFrame = 0;
		model.nextAnimPoint = 0;
		model.animObject.t = model.objectBeginTranslate; 
	} 
}
