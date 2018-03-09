package com.hexaware.ftp17.model;
import com.hexaware.ftp17.persistence.EmployeeDAO;
import com.hexaware.ftp17.persistence.LeaveDAO;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.text.ParseException;
import java.util.Date;
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
    Employee e100 = new Employee(100);
    Employee e101 = new Employee(101);
    assertNotEquals(e100, null);
    assertNotEquals(e100, new Integer(100));
    assertEquals(e100, new Employee(100));
    assertNotEquals(e101, new Employee(100));
    assertEquals(e100.hashCode(), new Employee(100).hashCode());
    assertEquals(e100.getEmpId(), new Employee(100).getEmpId());
    e101.setEmpId(100);
    assertEquals(e101, new Employee(100));
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
   * Tests the method to find whether entered ID is that of a manager or not works correctly.
   * @param dao mocking the dao class.
   * @throws ParseException for date.
   */
  @Test
  public final void testIsManager(@Mocked final EmployeeDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat ad = new SimpleDateFormat("yyyy/MM/dd");
        ArrayList<Employee> emp = new ArrayList<Employee>();
        ArrayList<Employee> emp1 = new ArrayList<Employee>();
        emp.add(new Employee(100, "Sruthi", "SruthiM@hexaware.com", 913456789, ad.parse("2017/12/27"), 300,
                              "HEXAVARSITY", 2));
        emp.add(new Employee(200, "Harish", "HarishR@hexaware.com", 953458789, ad.parse("2017/12/27"), 300,
                              "HEXAVARSITY", 4));
        emp1.add(new Employee(101, "Harish", "Hr@hexaware.com", 989456789, ad.parse("2017/12/27"), 301,
                              "HEXAVARSITY", 2));
        dao.findManager(300); result = emp;
        dao.findManager(301); result = emp1;
      }
    };
    new MockUp<Employee>() {
          @Mock
          EmployeeDAO dao() {
            return dao;
          }
      };
    SimpleDateFormat apd = new SimpleDateFormat("yyyy/MM/dd");
    Employee[] em = Employee.isManager(300);
    Employee[] em1 = Employee.isManager(301);
    assertNotEquals(em, null);
    assertEquals(2, em.length);
    assertEquals(1, em1.length);
    assertEquals(new Employee(100, "Sruthi", "SruthiM@hexaware.com", 913456789, apd.parse("2017/12/27"), 300,
                                "HEXAVARSITY", 2), em[0]);
    assertEquals(new Employee(200, "Harish", "HarishR@hexaware.com", 953458789, apd.parse("2017/12/27"), 300,
                                "HEXAVARSITY", 4), em[1]);
    assertNotEquals(em[0], em[1]);
    assertNotNull(em1[0]);
    assertNotNull(Employee.isManager(301));
  }

   /**
   * Tests the method to check the number of days.
   * @throws ParseException for date.
   */
  @Test
  public final void testCal() throws ParseException {
    SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
    String sd = "2018-02-06";
    Date startDate = null;
    startDate = start.parse(sd);
    Employee emp = new Employee();
    assertTrue(emp.cal(startDate, startDate, 1));
  }
 /**
   * Tests the method for start date and end date validation.
   * @throws ParseException for date.
   */
  @Test
  public final void testValidateDate() throws ParseException {
    SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
    String sd = "2018-02-06";
    Date startDate = null;
    Date endDate = null;
    startDate = start.parse(sd);
    String ed = "2018-02-04";
    endDate = start.parse(ed);
    Date doj = endDate;
    Employee emp = new Employee();
    assertFalse(emp.validateEndDate(startDate, endDate));
    assertTrue(emp.validateStartDate(startDate, doj));
  }
 /**
   * Tests the method to check for overlap in date.
   * @throws ParseException for date.
   * @throws IllegalArgumentException for date.
   */
  @Test (expected = IllegalArgumentException.class)
  public final void testOverLapDate() throws ParseException {
    SimpleDateFormat starts = new SimpleDateFormat("yyyy-MM-dd");
    new MockUp<LeaveDetails>() {
      @Mock
      Date getStartDate() throws ParseException {
          SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
        return start.parse("2018-06-21");
        }
      @Mock
      Date getEndDate() throws ParseException {
        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
        return start.parse("2018-06-21");
        }
      @Mock
      LeaveDetails[] listById(final int emp) throws ParseException {
        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
        LeaveDetails lev = new LeaveDetails(1, 1, start.parse("2018-06-21"), start.parse("2018-06-21"),
            LeaveType.EL, LeaveStatus.PENDING, "reason", start.parse("2018-02-06"), "comments", 1000);
        LeaveDetails[] ld = new LeaveDetails[1];
        ld[0] = lev;
        return ld;
      }
      };
    Employee.overlapDate(starts.parse("2018-06-21"), 1000);
    Employee.overlapEndDate(starts.parse("2018-06-21"), 1000);
  }
      /**
   * Tests the method to Converting the data to string.
   * @throws ParseException for date.
   */
  @Test
  public final void testToString() throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Employee e103 = new Employee(1001, "Rohan", "ro2gmail.com", 9898097645L, df.parse("2017-05-25"), 2000,
                                  "hexavarsity", 10);
    String st = " EmpId:" + e103.getEmpId() + " EmpName:" + e103.getEmpName() + " EmpEmail:" + e103.getEmpEmail()
        + " EmpMobile:" + e103.getMobNo() + "EmpDateOfJoining:" + e103.getDoj() + "EmpManager:" + e103.getManagerId()
          + " EmpDept:" + e103.getDept() + "LeaveAvailable:" + e103.getAvlBal();
    assertEquals(st, e103.toString());
    assertEquals(st, st);
    assertNotEquals(st, e103);
  }
    /**
   * Tests the apply leave method.
   * @throws ParseException if the dates are unable to parse.
   *@param ldao mocking the leave dao object.
   *@param dao mocking the employee dao object.
   */
  @Test
  public final void testapplyLeave(@Mocked final LeaveDAO ldao, @Mocked final EmployeeDAO dao) throws ParseException {
    final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    final Employee emp = new Employee(3000, "Sruthi", "SruthiM@hexaware.com", 913456789, sf.parse("2017-12-27"), 300,
                              "HEXAVARSITY", 10);
    new Expectations() {
      {
        ldao.applyLeave(5, sf.parse("2018-02-23"), sf.parse("2018-02-28"), "sick", 3000);
        result = 1;
        ldao.applyLeave(6, sf.parse("2018-02-28"), sf.parse("2018-02-23"), "sick", 3000);
        result = 0;
        dao.updateLeaveBal(5, 3000);
        result = 1;
        dao.find(3000); result = emp;
        }
      };
    new MockUp<LeaveDetails>() {
        @Mock
        LeaveDAO ldao() {
        return ldao;
      }
      };
    new MockUp<Employee>() {
        @Mock
        EmployeeDAO dao() {
        return dao;
      }
      };
    int eg = Employee.applyLeave(5, sf.parse("2018-02-23"), sf.parse("2018-02-28"), "sick", 3000);
    assertEquals(1, eg);
    int eg1 = Employee.applyLeave(6, sf.parse("2018-02-28"), sf.parse("2018-02-23"), "sick", 3000);
    assertEquals(0, eg1);

  }
}


