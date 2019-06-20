package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimerModelTest {

    TimerModel timerModel;
    private static final int sth = 0;

    @Before
    public void setUp(){
        timerModel = new TimerModel();
    }

    @Test
    public void testTimerIsDone() {
        timerModel.timerIsDone();
        timerModel.timerIsDone();
        assertFalse(false );
    }

    @Test
    public void testAddSecond() {
        //timerModel.addSecond();

        assertEquals(1, timerModel.getSeconds());
        assertFalse(true);
    }
}