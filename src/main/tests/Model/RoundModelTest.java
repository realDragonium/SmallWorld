package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundModelTest {

    private static final int maxRounds = 9;
    RoundModel roundModel;

    @Before
    public void setUp(){
        roundModel = new RoundModel(maxRounds);
    }

    @Test
    public void getMaxRounds() {
        roundModel.nextRound();

        assertEquals(2, roundModel.getRound(), maxRounds);
    }


}