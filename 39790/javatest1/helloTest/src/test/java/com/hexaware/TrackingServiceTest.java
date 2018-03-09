package com.hexaware;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import com.hexaware.TrackingService;
import com.hexaware.InvalidGoalException;
public class TrackingServiceTest
{   
    
    TrackingService service = new TrackingService();
    @Before
    public void SetUp()
    {
    
    service.setTotal(20);
    }
    @Test
    public void testAddProtein()
    {
    service.addProtein(10);
    assertEquals("Protein amount was not correct", 30, service.getTotal());    
    }

    @Test
    public void testRemoveProtein()
    {
        service.removeProtein(5);
        assertEquals("Protein amount was not correct", 15, service.getTotal());
    
    }
   @Test (expected=Exception.class)
    public void WhenGoalIsSetToLessThanZeroException() throws InvalidGoalException
    {
        service.setGoal(-6);
    }

}