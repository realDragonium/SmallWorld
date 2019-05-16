package moleculesampleapp;

import java.util.ArrayList;

import javafx.scene.Group;

public class Animation {

	private double playRate = 0.1;
	private ArrayList<AnimPoint> animationPoints = new ArrayList<AnimPoint>();
	private Transform objectBeginTransform;
	private AnimPoint nextPoint = null;
	private AnimPoint lastPoint;
	private Vector3 deltaPosition;
	private double animFrame = 0;
	
	public Animation() {
		Transform beginPoint = new Transform();
		beginPoint.position = new Vector3(0.0,0.0,0.0);
		beginPoint.rotation = new Rotation(0.0,0.0,0.0);
		animationPoints.add(new AnimPoint(beginPoint, 0.0));
	}
	
	public void addAnimationPoint(Transform transform, double time) {
		System.out.println(time);
		animationPoints.add(new AnimPoint(transform, time));
	}
	
	public void playAnimation(Object model, boolean loop) {
		objectBeginTransform = model.transform; 
		if(loop) {
			
		}
		
		else {
			
			if(nextPoint == null) {
				findNextAnimPoint();
				deltaPosition = confPosChange(model.transform);
			}
			else if(animFrame >= nextPoint.time) {
				System.out.println("volgende");
				findNextAnimPoint();
				deltaPosition = confPosChange(model.transform);
			}
			nextAnimFrame(model);
			animFrame += playRate;
		}
	}
	
	public void nextAnimFrame(Object model) {
		model.transform.position.plus(deltaPosition);
		model.updatePosition();
	}
	
	public Vector3 confPosChange(Transform transform) {
		double animTime = nextPoint.time - lastPoint.time;
		double deltaX = nextPoint.transform.position.x - lastPoint.transform.position.x;
		double deltaY = nextPoint.transform.position.y - lastPoint.transform.position.y;
		double deltaZ = nextPoint.transform.position.z - lastPoint.transform.position.z;
		double positionStepX = deltaX / (animTime / playRate);
		double positionStepY = deltaY / (animTime / playRate);
		double positionStepZ = deltaZ / (animTime / playRate);
		return new Vector3(positionStepX, positionStepY, positionStepZ);
		
	}
	
	public void findNextAnimPoint() {
		lastPoint = nextPoint;
		double time = 1000;
		if(nextPoint == null) {
			lastPoint = animationPoints.get(0);
			nextPoint = animationPoints.get(1);
			time = nextPoint.time;
		}
		for(int i = 2; i < animationPoints.size(); i++) {
			if(animationPoints.get(i).time < time && animationPoints.get(i).time > lastPoint.time) {
				System.out.println(animationPoints.get(i).time);
				nextPoint = animationPoints.get(i);
				time = nextPoint.time;
			}
		}
		System.out.println(nextPoint.transform.position.y);
	}
}
