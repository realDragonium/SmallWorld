package controlers;

import java.util.ArrayList;
import java.util.List;

import moleculesampleapp.Xform;

public class AnimationsControler {
	List<AnimationControler> animations = new ArrayList<>();
	List<AnimationControler> activeAnimations = new ArrayList<>(); 

	public void initAnimation(AnimationControler anim) {
		animations.add(anim);
	}
	
	public AnimationControler createAnimation(String animName) {
		AnimationControler newAnim = new AnimationControler(animName);
		initAnimation(newAnim);
		return newAnim;
	}
	
	public void animateObject(AnimationControler anim, Xform animObject){
		AnimationControler temp_anim = new AnimationControler();
		temp_anim.model.AnimPoints = anim.model.AnimPoints;
		temp_anim.model.animObject = animObject;
		temp_anim.animsControler = this;
		activeAnimations.add(temp_anim);
	}
	
	
	
	public void playAnimation(AnimationControler anim) {
		anim.resetAnimation();
		if(!activeAnimations.contains(anim)) {
			activeAnimations.add(anim);
		}
	}
	
	public void stopAnimation(AnimationControler anim) {
		if(activeAnimations.contains(anim)) {
			activeAnimations.remove(anim);
		}
	}
	
	public AnimationControler getAnimation(String name) {
		for(AnimationControler anim : animations) {
			System.out.println(anim.getName());
			if(anim.getName().equals(name)) {
				return anim;
			}
		}
		return null;
	}
	
	public void updateAnimations() {
		for(int i = 0; i < activeAnimations.size(); i++) {
			activeAnimations.get(i).nextFrame();
			
		}
		
	}
}
