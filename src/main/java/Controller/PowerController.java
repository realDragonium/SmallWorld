package Controller;

public class PowerController {
    private CombinationController combiCon;

    public void setCombiCon(CombinationController combiCon){
        this.combiCon = combiCon;
    }

    public String getId() {
        return "power";
    }
}
