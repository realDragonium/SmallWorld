package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurnModelTest {

    TurnModel turnModel;
    private static final int turnsPerRound = 4;

    @Before
    public void setUp(){
        turnModel = new TurnModel(turnsPerRound);
    }


    //Use-case test beeindigen beurt
    @Test
    public void testNextTurn() {
        turnModel.nextTurn();

        assertEquals(2, turnModel.getTurn());
    }

    @Test
    public void testNextTurnNextRound() {
        turnModel.nextTurn();
        turnModel.nextTurn();
        turnModel.nextTurn();
        turnModel.nextTurn();
        turnModel.nextTurn();

        assertEquals(2, turnModel.getTurn(), turnsPerRound);
    }
}