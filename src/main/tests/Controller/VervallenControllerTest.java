package Controller;

import Model.CombinationModel;
import Model.PlayerModel;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;

import java.sql.SQLOutput;

import static org.junit.Assert.*;

public class VervallenControllerTest {

    private CombinationController combCon;
    private String playerId;
    private boolean active;

    @Before
    public void setUp(){
        //gameCon.getCurrentPlayer().getActiveCombination().setToNonActive();
        CombinationController combCon = new CombinationController()
    }

    @Test
    public void testActive(){
        PlayerModel active = new PlayerModel("mannetje");
        assertFalse(mannetje.gameCon.isActive());
    }

    @Test
    public void inVerval() {
    }





}