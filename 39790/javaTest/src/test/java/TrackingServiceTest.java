import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

import static org.junit.Assert.*;

public class TrackingServiceTest{
    TrackingService service = new TrackingService();

    @Test
    public void NewTrackingServiceTotalZero(){
        assertEquals("Tracking Service total was not zero", 0, service.getTotal());

    }

    @Test
    public void WhenAddingProteinTotalIncreasesByThatAmount(){
        service.addProtein(10);
        assertEquals("Tracking Service total was not correct", 10, service.getTotal());

    }

    @Test
    public void WhenRemovingProteinTotalRemainsZero(){
        service.removeProtein(5);
        assertEquals("protein amount was not correct", 0, service.getTotal());
    }
}