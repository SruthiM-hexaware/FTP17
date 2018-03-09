package com.hexaware.ftp02.model;

import com.hexaware.ftp02.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import java.util.Date;
import java.text.ParseException;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployee() {
    Employee e100 = new Employee(3001, "SHREYASHREE MISHRA", 7683831702L,
                                 "ShreyashreeM@hexaware.com", "Hexavarsity", "2017-11-14", 2001, 10);
    Employee e101 = new Employee(3001, "SHREYASHREE MISHRA", 7683831702L,
                                 "ShreyashreeM@hexaware.com", "Hexavarsity", "2017-11-14", 2001, 10);
    assertEquals(e100.hashCode(), new Employee(3001, "SHREYASHREE MISHRA", 7683831702L,
                                               "ShreyashreeM@hexaware.com", "Hexavarsity",
                                               "2017-11-14", 2001, 10).hashCode());
    assertEquals(3001, e100.getEmpId());
    e101.setEmpId(3001);
    assertEquals("SHREYASHREE MISHRA", e100.getEmpName());
    e101.setEmpName("SHREYASHREE MISHRA");
    assertEquals(7683831702L, e100.getEmpPhone());
    e101.setEmpPhone(7683831702L);
    assertEquals("ShreyashreeM@hexaware.com", e100.getEmpEmail());
    e101.setEmpEmail("ShreyashreeM@hexaware.com");
    assertEquals("Hexavarsity", e100.getEmpDept());
    e101.setEmpDept("Hexavarsity");
    assertEquals("2017-11-14", e100.getEmpJoinDate());
    e101.setEmpJoinDate("2017-11-14");
    assertEquals(2001, e100.getEmpMgrId());
    e101.setEmpMgrId(2001);
    assertEquals(10, e100.getEmpLeaveBal());
    e101.setEmpLeaveBal(10);

    String s = "  EmpId:" + 3001 + "  EmpName:" + "SHREYASHREE MISHRA" + "  EmpPhoneNo:"
              + 7683831702L + "  EmpEmail:" + "ShreyashreeM@hexaware.com" + "  EmpDept:"
              + "Hexavarsity" + "  EmpJoinDate:" + "2017-11-14" + "  EmpMgrId:"
              + 2001 + "  EmpLeaveId:" + 10;
    String str = e101.toString();
    assertEquals(s, str);
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
   * tests that apply leave is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testApplyLeave(@Mocked final EmployeeDAO dao)throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
    final Date dt1 = sf.parse("25/12/2017");
    final Date dt2 = sf.parse("27/12/2017");
    final Date dt3 = sf.parse("20/12/2017");
    Employee emp = new Employee(2000, "Garima Joshi", 9039711012L,
                                 "GarimaJ@hexaware.com", "Hexavarsity", "2017-11-14", 5000, 12);
    Employee emp1 = new Employee(5000, "Krishna Kumar", 9876543210L, "krishnakumar@hexaware.com",
                                "Hexaware", "2013-07-07", 0, 10);
    String s = "Your request has been sent to the manager";
    String s1 = "Your request is approved";
    String s2 = "You have successfully applied for leave";
    String s3 = "You have already applied for leave";
    String str = emp.applyLeave(2000, 2, dt1, dt2, LeaveType.EL, "sick", dt3);
    String str1 = emp1.applyLeave(5000, 2, dt1, dt2, LeaveType.EL, "Wedding", dt3);
    String str2 = emp1.checkLeave(5000, 2, dt1, dt2, LeaveType.EL, "Wedding", dt3);
    String str3 = emp.checkLeave(2000, 2, dt1, dt2, LeaveType.EL, "sick", dt3);
    assertEquals(s, str);
    assertEquals(s1, str1);
    assertEquals(s2, str2);
    assertEquals(s2, str3);
  }
}
