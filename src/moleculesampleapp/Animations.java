package moleculesampleapp;

import java.util.ArrayList;

import javafx.scene.*;

public class Animations {
	
	private static ArrayList<Animation> activeAnimations = new ArrayList<Animation>();
	
	public static void doAnimations() {
		for(int i = 0; i < activeAnimations.size(); i++) {
			activeAnimations.get(i).doAction();
		}
	}
	
	public static void createAnimation() {
		
	}

	public static void moveToPoint(Group object, double newTranslateX, double newTranslateY, double newTranslateZ, float speed) {
		activeAnimations.add(new Animation(object, newTranslateX, newTranslateY, newTranslateZ, speed));
	}
	
	public static void moveToPoint() {
		
	}
	
	public static void moveToDirection() {
		
	}
	
	public static void destroyAnimation(Animation anim) {
		activeAnimations.remove(anim);
	}
}
