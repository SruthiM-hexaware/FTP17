package com.hexaware.ftp08.model;

import com.hexaware.ftp08.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.util.ArrayList;

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
    Employee e100 = new Employee(100, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12);
    Employee e101 = new Employee(101);
    //assertNotEquals(e101, null);
    assertNotEquals(e101, new Integer(100));
    assertEquals(e100, new Employee(100, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12));
    assertNotEquals(e101, new Employee(100));
    assertEquals(e101, new Employee(101));
    assertEquals(e100.hashCode(), new Employee(100, "Shubham Gupta",
                 "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12).hashCode());
    assertEquals(e101.getEmpId(), new Employee(101).getEmpId());
    /*e100.setEmpId(100);
    e100.setEmpName("Shubham Gupta");
    e100.setEmpEmail("ShubhamG2@gmail.com");
    e100.setEmpMobNo(8888888888L);
    e100.setEmpDptName("CEO");
    e100.setEmpMgrId(0);
    e100.setEmpLeaveBalance(12);
    //assertEquals(e100, new Employee(100, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12));
    assertEquals("Employee id mismatch", 100, e100.getEmpId());
    assertEquals("Employee name mismatch", "Shubham Gupta", e100.getEmpName());
    assertEquals("Employee email mismatch", "ShubhamG2@hexaware.com", e100.getEmpEmail());
    assertEquals("Employee mobile number mismatch", 8888888888L, e100.getEmpMobNo());
    assertEquals("Employee department name mismatch", "CEO", e100.getEmpDptName());
    assertEquals("Employee manager id mismatch", 0, e100.getEmpMgrId());
    assertEquals("Employee leave balance mismatch", 12, e100.getEmpLeaveBalance());*/
    assertEquals(100, e100.getEmpId());
    e101.setEmpId(100);
    assertEquals("Shubham Gupta", e100.getEmpName());
    e101.setEmpName("Shubham Gupta");
    assertEquals(8888888888L, e100.getEmpMobNo());
    e101.setEmpMobNo(8888888888L);
    assertEquals("ShubhamG2@hexaware.com", e100.getEmpEmail());
    e101.setEmpEmail("ShubhamG2@hexaware.com");
    assertEquals("CEO", e100.getEmpDptName());
    e101.setEmpDptName("CEO");
    assertEquals(0, e100.getEmpMgrId());
    e101.setEmpMgrId(0);
    assertEquals(12, e100.getEmpLeaveBalance());
    e101.setEmpLeaveBalance(12);
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
        es.add(new Employee(1, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12));
        es.add(new Employee(10, "Yuvaraj", "Yuvaraj@hexaware.com", 8787878787L, "BI", 1000, 2));
        es.add(new Employee(100, "Amit", "Amit@hexaware.com", 8675453212L, "HRM", 2000, 4));
        es.add(new Employee(200));
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
    assertEquals(4, es.length);
    assertEquals(new Employee(1, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12), es[0]);
    assertEquals(new Employee(10, "Yuvaraj", "Yuvaraj@hexaware.com", 8787878787L, "BI", 1000, 2), es[1]);
    assertEquals(new Employee(100, "Amit", "Amit@hexaware.com", 8675453212L, "HRM", 2000, 4), es[2]);
    assertEquals(new Employee(200), es[3]);
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
   * @throws ParseException to handle parse exception
   * @throws NullPointerException to handle parse exception
   */
  @Test
  public final void testApplyLeave(@Mocked final EmployeeDAO dao) throws ParseException, NullPointerException {
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee emp = new Employee(1000, "Shubham Gupta", "ShubhamG2@hexaware.com", 8888888888L, "CEO", 0, 12);
    Employee emp1 = new Employee(2000, "Yuvaraj", "Yuvaraj@hexaware.com", 8787878787L, "BI", 1000, 12);
    Employee emp2 = new Employee(3000, "Ankita", "Ankita@hexaware.com", 8767654544L, "BI", 2000, 12);
    Employee emp3 = new Employee(3001, "Priya", "Priya@hexaware.com", 8767676544L, "BI", 2001, 12);
    Employee emp4 = new Employee(4001, "Amit", "Amit@hexaware.com", 8734676544L, "BI", 2001, 12);
    Employee emp5 = new Employee(5001, "Ami", "Ami@hexaware.com", 8744676544L, "BI", 2001, 12);
    String s1 = "Your request is forwarded to manager";
    String s2 = "Your request is auto approved";
    String s3 = "please enter correct dates";
    String s4 = "Please enter date in correct format";
    String str1 = null;
    str1 = emp1.applyLeave("2018/12/27", "2018/12/29", 1000, "sick", 2000, 2, 1);
    String str3 = emp1.applyLeave("2018/12/27", "2018/12/29", 2000, "sick", 3000, 2, 1);
    String str2 = emp.applyLeave("2018/12/27", "2018/12/29", 0, "Wedding", 1000, 2, 1);
    String str4 = emp3.applyLeave("2018/12/27", "2018/12/29", 2001, "Wedding", 3001, 2, 1);
    String str5 = emp4.applyLeave("2018/12/27", "2018/12/26", 2001, "Wedding", 4001, 2, 1);
    String str7 = emp4.applyLeave("2018/1227", "2018/12/26", 2001, "Wedding", 4001, 2, 1);
    //Employee emp4 = new Employee(4001, "Amit", "Amit@hexaware.com", 8734676544L, "BI", 2001, 12);
    //Employee emp5 = new Employee(5001, "Ami", "Ami@hexaware.com", 8744676544L, "BI", 2001, 12);
    String str6 = emp4.applyLeave("2018/12/27", "2018/12/29", 2001, "Wedding", 5001, 2, 1);
    assertEquals(s1, str1);
    assertEquals(s2, str2);
    assertEquals(s1, str3);
    assertEquals(s1, str4);
    assertEquals(s3, str5);
    //assertNotEquals(s2, str5);
    assertEquals(s3, str5);
    assertNotEquals(s3, str6);
    assertEquals(s4, str7);

  }
  /**
   * tests that apply leave is handled correctly.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testListManager(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.listMgr(100); result = e100;
        dao.listMgr(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listManager(100);
    assertEquals(e100, e);

    e = Employee.listManager(-1);
    assertNull(e);
  }
}
