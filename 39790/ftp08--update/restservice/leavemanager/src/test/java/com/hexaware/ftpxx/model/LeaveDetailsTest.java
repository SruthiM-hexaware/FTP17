package com.hexaware.ftp08.model;
import com.hexaware.ftp08.persistence.LeaveDetailsDAO;

import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Test class for LeaveDetails.
 */
@RunWith(JMockit.class)
/**
 * Test class for LeaveDetails.
 */
public class LeaveDetailsTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {
  }
   /**
   * Tests the equals/hashcode methods of the leaveDetails class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testLeaveDetails()throws ParseException {
    Employee e100 = new Employee(100);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/20").getTime()),
                                         new java.sql.Date(sdf.parse("2017/12/23").getTime()), 3, "sick",
                                         LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/19").getTime()), null,
                                         LeaveStatus.PENDING, 2000);
    LeaveDetails l101 = new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/20").getTime()),
                                         new java.sql.Date(sdf.parse("2017/12/23").getTime()), 3, "sick",
                                         LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/19").getTime()), null,
                                         LeaveStatus.PENDING, 2000);
    assertEquals(l100.hashCode(), new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/20").getTime()),
                                         new java.sql.Date(sdf.parse("2017/12/23").getTime()), 3, "sick",
                                         LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/19").getTime()), null,
                                         LeaveStatus.PENDING, 2000).hashCode());
    LeaveDetails ll = new LeaveDetails();
    assertEquals(1, l100.getLeaId());
    l101.setLeaId(1);
    assertEquals(3, l100.getLeaNoOfDays());
    l101.setLeaNoOfDays(3);
    assertEquals(new java.sql.Date(sdf.parse("2017/12/20").getTime()), l100.getLeaStartDate());
    l101.setLeaStartDate(new java.sql.Date(sdf.parse("2017/12/20").getTime()));
    assertEquals(new java.sql.Date(sdf.parse("2017/12/23").getTime()), l100.getLeaEndDate());
    l101.setLeaEndDate(new java.sql.Date(sdf.parse("2017/12/23").getTime()));
    assertEquals(LeaveType.EL, l100.getLeaType());
    l101.setLeaType(LeaveType.EL);
    assertEquals(LeaveStatus.PENDING, l100.getLeaStatus());
    l101.setLeaStatus(LeaveStatus.PENDING);
    assertEquals(2000, l100.getEmplId());
    l101.setEmplId(2000);
    assertEquals("sick", l100.getLeaReason());
    l101.setLeaReason("sick");
    assertEquals(new java.sql.Date(sdf.parse("2017/12/19").getTime()), l100.getLeaAppliedOn());
    l101.setLeaAppliedOn(new java.sql.Date(sdf.parse("2017/12/19").getTime()));
    assertEquals(null, l100.getLeaMgrComments());
    l101.setLeaMgrComments(null);
    String toS = "| leave id          |" + 1 + "\n| employee id       |"
            + 2000 + "\n| start date        |" + "2017-12-20" + "\n| end date          |"
            + "2017-12-23" + "\n| number of days    |" + 3 + "\n| leave reason      |"
            + "sick" + "\n| leave type        |" + "EL" + "\n| leave applied on  |"
            + "2017-12-19" + "\n| manager comments  |" + null + "\n| leave status      |" + "PENDING";
    String s = l100.toString();
    assertEquals(toS, s);
    System.out.println("To string method of employee tested");
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistPendingApplications(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        dao.finds(2000);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/21").getTime()),
                                new java.sql.Date(sdf.parse("2017/12/23").getTime()), 3, "sick",
                                LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/19").getTime()), null,
                                LeaveStatus.PENDING, 2000));
        ld.add(new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/11").getTime()),
                                new java.sql.Date(sdf.parse("2017/12/14").getTime()), 3, "sick",
                                LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/10").getTime()), null,
                                LeaveStatus.PENDING, 2000));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listPendingApplications(2000);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/21").getTime()),
                              new java.sql.Date(sdf.parse("2017/12/23").getTime()), 3, "sick",
                              LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/19").getTime()), null,
                              LeaveStatus.PENDING, 2000);
    ld1[1] = new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/11").getTime()),
                              new java.sql.Date(sdf.parse("2017/12/14").getTime()), 3, "sick",
                              LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/10").getTime()), null,
                              LeaveStatus.PENDING, 2000);
    assertArrayEquals(ld1, ld);
    System.out.println("Test for Pending leave");
  }

  /**
   * Tests that a fetch approve method works correctly.
   * @throws ParseException to handle parse exception.
   * @param dao mocking the dao class
   */
  @Test
  public final void testApproveLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.approve("ENJOY", "APPROVED", 16, 2001);
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l1 = new LeaveDetails(16, new java.sql.Date(sf.parse("2017/12/25").getTime()),
                     new java.sql.Date(sf.parse("2017/12/27").getTime()), 3, "Picnic",
                     LeaveType.EL, new java.sql.Date(sf.parse("2017/12/21").getTime()), "Enjoy",
                     LeaveStatus.APPROVED, 2001);
    String ld = l1.approveLeave("ENJOY", 16, 2001);
    String l2 = "LEAVE IS APPROVED";
    assertEquals(l2, ld);
  }
   /**
   * Tests that a fetch deny method works correctly.
   * @throws ParseException to handle parse exception.
   * @param dao mocking the dao class
   */
  @Test
  public final void testDenyLeave(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    /*new Expectations() {
      {
        dao.deny("Client Visit", "DENIED", 17, 3001);
        dao.leaveBal(7, 3001);
      }
    };*/
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l3 = new LeaveDetails(17, new java.sql.Date(sdf.parse("2017/12/22").getTime()),
                     new java.sql.Date(sdf.parse("2017/12/26").getTime()), 5, "Holiday",
                     LeaveType.EL, new java.sql.Date(sdf.parse("2017/12/21").getTime()),
                     "Client Visit", LeaveStatus.DENIED, 3001);
    String ld1 = l3.denyLeave("Client Visit", 17, 3001);
    String l4 = "LEAVE IS DENIED";
    assertEquals(l4, ld1);
  }

  /**
   * Tests that a list with some LeaveDetails is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException which throws ParseException
   */
  @Test
  public final void testListLeaveDetails(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        dao.lis(2000);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/23").getTime()),
                                new java.sql.Date(sdf.parse("2017/12/25").getTime()), 3, "sick", LeaveType.EL,
                                new java.sql.Date(sdf.parse("2017/12/12").getTime()), "enjoy",
                                LeaveStatus.PENDING, 2000));
        ld.add(new LeaveDetails(4, new java.sql.Date(sdf.parse("2017/12/26").getTime()),
                                new java.sql.Date(sdf.parse("2017/12/29").getTime()), 3, "wedding", LeaveType.EL,
                                new java.sql.Date(sdf.parse("2017/12/18").getTime()), "noooo",
                                LeaveStatus.PENDING, 3000));

        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
        LeaveDetailsDAO dao() {
          return dao;
        }
    };
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listLeaveDetails(2000);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(1, new java.sql.Date(sdf.parse("2017/12/23").getTime()),
                              new java.sql.Date(sdf.parse("2017/12/25").getTime()), 3, "sick", LeaveType.EL,
                              new java.sql.Date(sdf.parse("2017/12/12").getTime()), "enjoy", LeaveStatus.PENDING,
                              2000);
    ld1[1] = new LeaveDetails(4, new java.sql.Date(sdf.parse("2017/12/26").getTime()),
                                new java.sql.Date(sdf.parse("2017/12/29").getTime()), 3, "wedding", LeaveType.EL,
                                new java.sql.Date(sdf.parse("2017/12/18").getTime()), "noooo",
                                LeaveStatus.PENDING, 3000);
    assertArrayEquals(ld1, ld);
    System.out.println("Test for leave details");
  }
  /**
   * Tests that a list with some LeaveDetails is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListByLeaveId(@Mocked final LeaveDetailsDAO dao) {
    final LeaveDetails e100 = new LeaveDetails(100);
    new Expectations() {
      {
        dao.send(100); result = e100;
        dao.send(-1); result = null;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails e = LeaveDetails.listByLeaveId(100);
    assertEquals(e100, e);

    e = LeaveDetails.listByLeaveId(-1);
    assertNull(e);
  }
}
