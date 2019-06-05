package controlers;

import java.util.ArrayList;
import java.util.List;

import moleculesampleapp.Xform;

public class AnimationsControler {
	List<views.AnimationControler> animations = new ArrayList<>();
	List<views.AnimationControler> activeAnimations = new ArrayList<>();

	public void initAnimation(views.AnimationControler anim) {
		animations.add(anim);
	}
	
	public void createAnimation(String animName) {
		initAnimation(new views.AnimationControler(animName));
	}
	
	public void animateObject(views.AnimationControler anim, Xform animObject) throws CloneNotSupportedException{
		views.AnimationControler temp_anim = new views.AnimationControler();
		temp_anim.model.AnimPoints = anim.model.AnimPoints;
		temp_anim.model.animObject = animObject;
		temp_anim.animsControler = this;
		activeAnimations.add(temp_anim);
	}
	
	
	
	public void playAnimation(views.AnimationControler anim) {
		anim.resetAnimation();
		if(!activeAnimations.contains(anim)) {
			activeAnimations.add(anim);
		}
	}
	
	public void stopAnimation(views.AnimationControler anim) {
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
