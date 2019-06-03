package moleculesampleapp;

import java.util.ArrayList;

public class AnimationController {
	public static ArrayList<Animation> animations = new ArrayList<Animation>();
	
	public static void playAnimations() {
		for(int i = 0; i < animations.size(); i++) {
			animations.get(i).playAnimation(false);
			
		}
	}
	
	public static void addAnimation(Animation anim) {
		animations.add(anim);
	}
}
