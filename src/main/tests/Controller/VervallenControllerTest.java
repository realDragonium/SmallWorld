package Controller;

import org.junit.Before;
import org.junit.Test;

public class VervallenControllerTest {

    VervallenController vervCon;
    CombinationController combCon;

    //testen of een speler active of nonactive kan zijn

    @Before
    public void setUp(){
        //gameCon.getCurrentPlayer().getActiveCombination().setToNonActive();
        //CombinationController combCon = new CombinationController()
        combCon = new CombinationController();
        vervCon = new VervallenController();
        vervCon.inVerval();
    }

    @Test
    public void testActive(){

        //PlayerModel active = new PlayerModel("mannetje");
        //assertFalse(mannetje.gameCon.isActive());
    }

    @Test
    public void inVerval() {

    }





}