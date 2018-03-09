package com.hexaware.ftp02.model;

import com.hexaware.ftp02.persistence.EmployeeDAO;
import com.hexaware.ftp02.persistence.LeaveDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;
import java.text.ParseException;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 * Test class for LeaveDetails.
 */
@RunWith(JMockit.class)
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
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all");
    LeaveDetails l101 = new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all");
    assertEquals(l100.hashCode(), new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                                   LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                                   sf.parse("2017/12/14"), "no no not at all").hashCode());
    assertEquals(4, l100.getLeaveId());
    l101.setLeaveId(4);
    assertEquals(2, l100.getLeaveDays());
    l101.setLeaveDays(2);
    assertEquals(sf.parse("2017/12/19"), l100.getLeaveStartDate());
    l101.setLeaveStartDate(sf.parse("2017/12/19"));
    assertEquals(sf.parse("2017/12/20"), l100.getLeaveEndDate());
    l101.setLeaveEndDate(sf.parse("2017/12/20"));
    assertEquals(LeaveType.EL, l100.getLeaveType());
    l101.setLeaveType(LeaveType.EL);
    assertEquals(LeaveStatus.DENIED, l100.getLeaveStatus());
    l101.setLeaveStatus(LeaveStatus.DENIED);
    assertEquals(2000, l100.getLeaveEmpId());
    l101.setLeaveEmpId(2000);
    assertEquals("wedding", l100.getLeaveReason());
    l101.setLeaveReason("wedding");
    assertEquals(sf.parse("2017/12/14"), l100.getLeaveAppliedOn());
    l101.setLeaveAppliedOn(sf.parse("2017/12/14"));
    assertEquals("no no not at all", l100.getLeaveMgrComment());
    l101.setLeaveMgrComment("no no not at all");
    String toS = "  leaveId: " + 4 + "  leaveDays: " + 2 + "  leaveStartDate: "
              + "19/12/2017" + "  leaveEndDate: " + "20/12/2017" + "  leaveEmpId: "
              + 2000 + "  leaveReason: " + "wedding"
              + "  leaveAppliedOn: " + "14/12/2017"
              + "  leaveMgrComment: " + "no no not at all"
              + "  leavestatus: " + "DENIED" + "  leavetype: " + "EL";
    String s = l100.toString();
    assertEquals(toS, s);
    System.out.println("To string method of empplyee tested");
  }

  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        es.add(new Employee(1));
        es.add(new Employee(10));
        es.add(new Employee(100));
        dao.list(); result = es;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(3, es.length);
    assertEquals(new Employee(1), es[0]);
    assertEquals(new Employee(10), es[1]);
    assertEquals(new Employee(100), es[2]);
  }

    /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.find(100); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(100);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistPendingApplication(@Mocked final LeaveDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.finds(2000);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(5, 2, sf.parse("2018/01/01"), sf.parse("2018/01/02"),
                                LeaveType.EL, LeaveStatus.PENDING, 2001, "ANY", sf.parse("2017/12/14"),
                                "approved"));
        ld.add(new LeaveDetails(5, 2, sf.parse("2018/01/02"), sf.parse("2018/01/02"),
                                LeaveType.EL, LeaveStatus.PENDING, 2001, "ANY", sf.parse("2017/12/14"),
                                "approved"));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listPendingApplication(2000);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(5, 2, sf.parse("2018/01/01"), sf.parse("2018/01/02"),
                                LeaveType.EL, LeaveStatus.PENDING, 2001, "ANY", sf.parse("2017/12/14"),
                                "approved");
    ld1[1] = new LeaveDetails(5, 2, sf.parse("2018/01/02"), sf.parse("2018/01/02"),
                                LeaveType.EL, LeaveStatus.PENDING, 2001, "ANY", sf.parse("2017/12/14"),
                                "approved");
    assertArrayEquals(ld1, ld);
    System.out.println("Pending leave Test");
  }
  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistLeaveDetails(@Mocked final LeaveDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.find(2000);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all"));
        ld.add(new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all"));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listLeaveDetails(2000);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all");
    ld1[1] = new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all");
    assertArrayEquals(ld1, ld);
    System.out.println("List_Leave_Details Test");
  }
   /**
   * Tests that approve and deny operates correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testapproveDeny(@Mocked final LeaveDAO dao) throws ParseException {
    new Expectations() {
      {
        dao.action(4, LeaveStatus.APPROVED, "Exam");
        dao.updateBal(4);
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(4, 2, sf.parse("2017/12/19"), sf.parse("2017/12/20"),
                                         LeaveType.EL, LeaveStatus.DENIED, 2000, "wedding",
                                         sf.parse("2017/12/14"), "no no not at all");
    String ld = l100.approveDeny(4, "APPROVED", 2000, 12, "Exam");
    String ld1 = "Your leave is approved!";
    assertEquals(ld1, ld);
    String ld2 = l100.approveDeny(4, "DENIED", 2000, 12, "Exam");
    String ld3 = "Already Denied";
    assertEquals(ld3, ld2);
    String ld4 = l100.approveDeny(4, "KEEP PENDING", 2000, 12, "Exam");
    String ld5 = "Done";
    assertEquals(ld5, ld4);
  }
}
