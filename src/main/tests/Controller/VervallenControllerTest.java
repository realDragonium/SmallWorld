package Controller;

import Model.CombinationModel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author : Martijn van der Steen
 * @version : Juni 2019
 */

public class VervallenControllerTest {

    VervallenController vervCon;
    CombinationController combCon;
    CombinationModel combmodel;


    /**
     *
     */

    //testen of een speler active of nonactive kan zijn
    @Before
    public void setUp(){
        combmodel = new CombinationModel(" ", " ");
        combCon = new CombinationController();
        //vervCon = new VervallenController();
    }


    /**
     *
     *
     *
     *
     */

    @Test
    public void testActive(){

        assertTrue(combmodel.isActive());

    }

    @Test
    public void inVerval() {

    }





}