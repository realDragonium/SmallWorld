package Model;

import Controller.PlayerController;
import Objects.RaceFiche;
import Observable.AreaObservable;
import Observer.AreaObserver;
import Enum.AreaProperty;
import Enum.AreaType;

import java.util.*;
import java.util.stream.IntStream;

public class AreaModel implements AreaObservable {

    private Stack<RaceFiche> raceFiches = new Stack<>();
    private boolean active = false;
    private AreaObserver observer;
    private String id;
    public int fichesCount;
    public PlayerController player;
    private AreaType type;
    private boolean nextToWater = false;
    private boolean borderArea = false;
    private AreaProperty specialProperty = AreaProperty.none;
    private List<String> neighbours = new ArrayList<>();
    private boolean attackAble = true;

    public AreaModel(String id) {
        this.id = id;
//        Map<String, Object> info = SceneManager.getInstance().getApp().getFirebaseService().getAreaSettings(id);
        IntStream.range(0, (int) (Math.random() * 3)).forEach(i -> raceFiches.push(new RaceFiche()));
        fichesCount = raceFiches.size();
    }

    public void setFiches(int fiches){
        IntStream.range(0, fiches).forEach(o -> {
            raceFiches = new Stack<>();
            raceFiches.push(new RaceFiche());
        });
    }

    public void setNeighbours(List<String> neighbour){
        neighbours = neighbour;
    }

    public void setBorderArea(boolean bArea){
        borderArea = bArea;
    }

    public void setAreaType(String type){
        this.specialProperty = AreaProperty.valueOf(type);
    }


    public String getId() {
        return id;
    }

    public void changeActive() {
        active = !active;
        fichesCount = raceFiches.size();
        notifyObserver();
    }

    //+2 vanwege 1 extra per fiche en het land is 2 verdedingspunt waard
    public int numbersNeeded() {
        return raceFiches.size() + 2;
    }

    //Ze worden overschreven omdat getAllFiches de hele lijst al mee geeft
    public void setFiches(Stack<RaceFiche> fiches) {
        raceFiches = fiches;
        fichesCount = raceFiches.size();
        notifyObserver();
    }

    public Stack<RaceFiche> getAllFiches() {
        Stack<RaceFiche> tempFiches = raceFiches;
        raceFiches = new Stack<>();
        fichesCount = raceFiches.size();
        return tempFiches;
    }

    public AreaProperty getSpecialProp(){
        return specialProperty;
    }

    public boolean isNextToWater(){
        return nextToWater;
    }

    public Stack<RaceFiche> getAllButOne(){
        Stack<RaceFiche> temp = new Stack<>();
        if(raceFiches.size() > 0) {
            RaceFiche tempFiche = raceFiches.pop();
            temp  = raceFiches;
            raceFiches = new Stack<>();
            raceFiches.add(tempFiche);
            fichesCount = raceFiches.size();
            notifyObserver();
        }
        return temp;
    }

    @Override
    public void register(AreaObserver ao) {
        observer = ao;
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public int getNumberOfFiches() {
        return raceFiches.size();
    }

    public RaceFiche getOneFiche() {
        RaceFiche tempFiche = raceFiches.pop();
        notifyObserver();
        return tempFiche;
    }

    public void addFiche(RaceFiche fiche) {
        raceFiches.add(fiche);
        notifyObserver();
    }

    public AreaType getAreaType() {
        return type;
    }

    public void setAttackAble(boolean attackAble) {
        this.attackAble = attackAble;
    }
}
