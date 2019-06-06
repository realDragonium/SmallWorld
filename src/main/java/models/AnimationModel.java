package models;

import java.util.ArrayList;
import java.util.List;

import controlers.AnimPointControler;
import javafx.scene.transform.Translate;
import moleculesampleapp.Xform;

public class AnimationModel {
	public String name;
	public List<AnimPointControler> AnimPoints = new ArrayList<>();
	public int nextAnimPoint;
	public Translate deltaPosition = new Translate(0,0,0);
    public double deltaRotationX = 0;
    public double deltaRotationY = 0;
    public double deltaRotationZ= 0;
    public int curFrame = -1;
    public Xform animObject;
    public Translate objectBeginTranslate;

	public void addAnimPoint(AnimPointControler point) {
		int index = 0;
		for(AnimPointControler animPoint : AnimPoints) {
			System.out.println("point" + point.model.time);
			System.out.println("animPoint" + animPoint.model.time);
			if(animPoint.model.time > point.model.time) {
				break;
			}
			index++;
		}
		System.out.println ("index: " + index);
		AnimPoints.add(index, point);
	}
	
	
}
