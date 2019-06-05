package main.java.models;

public class SpecialekrachtfichesModel {
    private String name;
    //private powerFicheAction;
    private boolean powerActive;
    private boolean powerUsable;


    public SpecialekrachtfichesModel(String name, boolean powerActive, boolean powerUsable){
        this.name = name;
        this.powerActive = powerActive;
        this.powerUsable = powerUsable;

    }

    public String getName() {
        return name;
    }

    //public KrachtAction doAction(){}


    public boolean isUsable() {
        return powerUsable;
    }
    public boolean isActive(){
        return powerActive;
     }

}
