
package com.hexaware.ftp17.model;

import com.hexaware.ftp17.persistence.LeaveDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.ParseException;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

  /**
   * Testing the LeaveDetails class.
   */

@RunWith(JMockit.class)
public class LeaveDetailsTest {
  /**
   * tests that LeaveDetails constructor is handled correctly.
   * @throws ParseException if the dates are unable to parse.
   */

  @Test
    public final void testLeaveDetails()throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(1, 5, sf.parse("2018/02/07"), sf.parse("2018/02/11"),
                                          LeaveType.EL, LeaveStatus.PENDING, "sick", sf.parse("2018/02/06"),
                                            "wait", 100);
    LeaveDetails l101 = new LeaveDetails(2, 4, sf.parse("2018/02/11"), sf.parse("2018/02/14"),
                                          LeaveType.EL, LeaveStatus.PENDING, "Marriage", sf.parse("2018/02/09"),
                                            "wait", 101);
    assertNotEquals(l100, null);
    assertNotEquals(l101, null);
    assertNotEquals(l100, new Integer(100));
    assertNotEquals(l101, new Integer(102));
    assertEquals(l100, new LeaveDetails(1, 5, sf.parse("2018/02/07"), sf.parse("2018/02/11"), LeaveType.EL,
                                          LeaveStatus.PENDING, "sick", sf.parse("2018/02/06"), "wait", 100));
    assertEquals(l101, new LeaveDetails(2, 4, sf.parse("2018/02/11"), sf.parse("2018/02/14"), LeaveType.valueOf("EL"),
                                          LeaveStatus.valueOf("PENDING"), "Marriage", sf.parse("2018/02/09"),
                                            "wait", 101));
    assertNotEquals(l101, new LeaveDetails(1, 5, sf.parse("2018/02/07"), sf.parse("2018/02/11"),
                                            LeaveType.EL, LeaveStatus.PENDING, "sick", sf.parse("2018/02/06"),
                                              "wait", 100));
    assertEquals(l100.hashCode(), new LeaveDetails(1, 5, sf.parse("2018/02/07"), sf.parse("2018/02/11"), LeaveType.EL,
                                                    LeaveStatus.PENDING, "sick", sf.parse("2018/02/06"),
                                                      "wait", 100).hashCode());
    assertEquals(l101.hashCode(), new LeaveDetails(2, 4, sf.parse("2018/02/11"), sf.parse("2018/02/14"), LeaveType.EL,
                                                    LeaveStatus.PENDING, "Marriage", sf.parse("2018/02/09"),
                                                      "wait", 101).hashCode());
    assertEquals(1, l100.getLeaveId());
    l101.setLeaveId(1);
    assertEquals(5, l100.getNoOfDays());
    l101.setNoOfDays(5);
    assertEquals(sf.parse("2018/02/07"), l100.getStartDate());
    l101.setStartDate(sf.parse("2018/02/07"));
    assertEquals(sf.parse("2018/02/11"), l100.getEndDate());
    l101.setEndDate(sf.parse("2018/02/11"));
    assertEquals(LeaveType.EL, l100.getLeaveType());
    l101.setLeaveType(LeaveType.EL);
    assertEquals(LeaveStatus.PENDING, l100.getLeaveStatus());
    l101.setLeaveStatus(LeaveStatus.PENDING);
    assertEquals("sick", l100.getLeaveReason());
    l101.setLeaveReason("sick");
    assertEquals(sf.parse("2018/02/06"), l100.getLeaveApplied());
    l101.setLeaveApplied(sf.parse("2018/02/06"));
    assertEquals("wait", l100.getLeaveComments());
    l101.setLeaveComments("wait");
    assertEquals(100, l100.getEmpId());
    l101.setEmpId(100);
    assertEquals(l101, l100);
  }
/**
   * tests that LeaveDetails list is handled correctly.
   * @param ldao mocking the dao class
   * @throws ParseException if the dates are unable to parse.
   */

  @Test
    public final void testLeaveHistory(@Mocked final LeaveDAO ldao) throws ParseException {
    final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    new Expectations() {
        {
          ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
          ld.add(new LeaveDetails(100, 5, sf.parse("2018-02-04"), sf.parse("2018-02-05"),
                                      LeaveType.valueOf("EL"), LeaveStatus.valueOf("PENDING"),
                                        "Sick", sf.parse("2018-02-01"), "", 1000));
          ld.add(new LeaveDetails(101, 5, sf.parse("2018-02-04"), sf.parse("2018-02-05"),
                      LeaveType.valueOf("EL"), LeaveStatus.valueOf("PENDING"), "Sick", sf.parse("2018-02-01"),
                        "", 1000));

          ldao.findLeaveHistory(1000); result = ld;
        }
    };
    new MockUp<LeaveDetails>() {
        @Mock
        LeaveDAO ldao() {
        return ldao;
        }
    };
    LeaveDetails[] l = LeaveDetails.listById(1000);
    assertEquals(2, l.length);
    assertEquals(new LeaveDetails(100, 5, sf.parse("2018-02-04"), sf.parse("2018-02-05"),
                    LeaveType.valueOf("EL"), LeaveStatus.valueOf("PENDING"), "Sick", sf.parse("2018-02-01"),
                        "", 1000), l[0]);
    assertEquals(new LeaveDetails(101, 5, sf.parse("2018-02-04"), sf.parse("2018-02-05"),
                        LeaveType.valueOf("EL"), LeaveStatus.valueOf("PENDING"), "Sick", sf.parse("2018-02-01"),
                            "", 1000), l[1]);
  }
/**
* Tests that a list with no data is handled correctly.
* @param ldao mocking the dao class
*/
  @Test
  public final void testFindPendingEmpty(@Mocked final LeaveDAO ldao) {
    new Expectations() {
      {
        ldao.findPendingLeave(1000); result = new ArrayList<LeaveDetails>();
      }
      };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDAO ldao() {
        return ldao;
      }
    };
    LeaveDetails[] ld = LeaveDetails.pendingLeave(1000);
    assertEquals(0, ld.length);
  }

/**
* Tests that a list with some pending leave is handled correctly.
* @param ldao mocking the dao class
*/

  @Test
  public final void testFindPending(@Mocked final LeaveDAO ldao) {
    new Expectations() {
      {
        ArrayList<LeaveDetails> pl = new ArrayList<LeaveDetails>();
        pl.add(new LeaveDetails(1));
        pl.add(new LeaveDetails(2));
        pl.add(new LeaveDetails(3));
        ldao.findPendingLeave(150); result = pl;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDAO ldao() {
        return ldao;
      }
    };
    LeaveDetails[] pl = LeaveDetails.pendingLeave(150);
    assertEquals(3, pl.length);
    assertEquals(new LeaveDetails(1), pl[0]);
    assertNotEquals(new LeaveDetails(1), pl[2]);
    assertEquals(new LeaveDetails(2), pl[1]);
    assertEquals(new LeaveDetails(3), pl[2]);
    assertNotNull(pl[0]);
  }
}
