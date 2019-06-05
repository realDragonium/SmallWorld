package main.java.models;

public class specialekrachtModel implements PowerAction {
    private String name;
    private KrachtAction krachtAction;
    public boolean Usable;
    private int numberOfFiches;

    public specialekrachtModel (String name, boolean Usable, int numberOfFiches){
        this.name = name;
        this.Usable = Usable;
        this.numberOfFiches = numberOfFiches;
    }

    public String getName() {
        return name;
    }

    public void doAction(){
        return;
    }

    public boolean isUsable() {
        return Usable;
    }

    public int getNumberOfFiches() {
        return numberOfFiches;
    }
}
