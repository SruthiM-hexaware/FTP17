package com.hexaware.ftp17.model;

import com.hexaware.ftp17.persistence.DbConnection;
import com.hexaware.ftp17.persistence.EmployeeDAO;
import java.util.Objects;
import java.util.List;
import java.util.Date;
/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empId;
  private String empName;
  private String empEmail;
  private long mobNo;
  private Date doj;
  private int managerId;
  private String dept;
  private int avlBalance;



  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId) && Objects.equals(empName, emp.empName)
        && Objects.equals(empEmail, emp.empEmail) && Objects.equals(mobNo, emp.mobNo)
        && Objects.equals(doj, emp.doj)
        && Objects.equals(managerId, emp.managerId) && Objects.equals(dept, emp.dept)
        && Objects.equals(avlBalance, emp.avlBalance)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId, empName, empEmail, mobNo, doj, managerId, dept, avlBalance);
  }

  /**
   * @param argEmpId to initialize employee id.
   */
  public Employee(final int argEmpId) {
    this.empId = argEmpId;
  }
   /**
   * to initialize employee id.
   */
  public Employee() {
    this.empId = 0;
    this.empName = null;
    this.empEmail = null;
    this.mobNo = 0;
    this.doj =  null;
    this.managerId = 0;
    this.dept = null;
    this.avlBalance = 0;
  }
  /**
   * @param argEmpId to initialize employee id.
   * @param argEmpName to initialize employee name.
   * @param argEmpEmail to initialize employee email.
   * @param argMobNo to initialize employee mobile no.
   * @param argDateOfJoining to initialize employee Date of Joining.
   * @param argManagerId to initialize employee manager id.
   * @param argDept to initialize employee department
   * @param argAvlBalance to initialize employee available balance.
  */
  public Employee(final int argEmpId, final String argEmpName, final String argEmpEmail,
                   final long argMobNo, final Date argDateOfJoining, final int argManagerId, final String argDept,
                    final int argAvlBalance) {
    this.empId = argEmpId;
    this.empName = argEmpName;
    this.empEmail = argEmpEmail;
    this.mobNo = argMobNo;
    this.doj =  argDateOfJoining;
    this.managerId = argManagerId;
    this.dept = argDept;
    this.avlBalance = argAvlBalance;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * Gets the EmployeeName.
   * @return this Employee's Name.
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   * Gets the EmployeeEmail.
   * @return this Employee's Email.
   */
  public final String getEmpEmail() {
    return empEmail;
  }

  /**
   * Gets the EmployeeMobNo.
   * @return this Employee's Mobile No.
   */
  public final long getMobNo() {
    return mobNo;
  }

  /**
   * Gets the Employee Date of Joining.
   * @return this Employee's  Date of Joining.
   */
  public final Date getDoj() {
    return doj;
  }

  /**
   * Gets the EmployeeManagerId.
   * @return this Employee's ManagerId.
   */
  public final int getManagerId() {
    return managerId;
  }

  /**
   * Gets the EmployeeDept.
   * @return this Employee's Department.
   */
  public final String getDept() {
    return dept;
  }

  /**
   * Gets the EmployeeAvlBalance.
   * @return this Employee's AvlLeave.
   */
  public final int getAvlBal() {
    return avlBalance;
  }

  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   *
   * @param argEmpName to set employee name.
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }
  /**
   *
   * @param argEmpEmail to set employee email.
   */
  public final void setEmpEmail(final String argEmpEmail) {
    this.empEmail = argEmpEmail;
  }

  /**
   *
   * @param argMobNo to set employee Mobileno.
   */
  public final void setMobNo(final long argMobNo) {
    this.mobNo = argMobNo;
  }
  /**
   *
   * @param argDateOfJoining to set employee Date of Joining.
   */
  public final void setDoj(final Date argDateOfJoining) {
    this.doj = argDateOfJoining;
  }

  /**
   *
   * @param argManagerId to set employee Managerid.
   */
  public final void setManagerId(final int argManagerId) {
    this.managerId = argManagerId;
  }
  /**
   *
   * @param argDept to set employee Department.
   */
  public final void setDept(final String argDept) {
    this.dept = argDept;
  }
  /**
   *
   * @param argAvlBal to set employee's available leave.
   */
  public final void setAvlBal(final int argAvlBal) {
    this.avlBalance = argAvlBal;
  }

  /**
   * The dao for employee.
   */
  private static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }
   /**
   * calculation for leave balance updation.
   * @param day indicates the avaailbale leaves.
   * @param empId employee id.
   * @return to return the updated Leave balance.
   */
  public static int avlBalanceUpdation(final int day, final int empId) {
    int updateStatus = dao().avlBalUpdate(day, empId);
    if (updateStatus == 1) {
      System.out.println("leave balance updated");
    }
    return updateStatus;
  }
    /**
   * calculation for leave balance updation.
   * @param strtDate is the start date of leave.
   * @param empId is the employee id.
   * @throws IllegalArgumentException for date .
   */
  public static void overlapDate(final Date strtDate, final int empId) throws IllegalArgumentException {
    LeaveDetails[] leavedetail = LeaveDetails.listById(empId);
    for (LeaveDetails ld : leavedetail) {
      if (strtDate.compareTo(ld.getStartDate()) >= 0 && strtDate.compareTo(ld.getEndDate()) <= 0) {
        throw new IllegalArgumentException("--Sorry !! You have already applied leave on this day--");
      }
    }
  }
    /**
    * calculation for leave balance updation.
     * @param endDate employee id.
     * @param empId is the employee id.
     * @throws IllegalArgumentException for date .
    */
  public static void overlapEndDate(final Date endDate, final int empId) throws IllegalArgumentException {
    LeaveDetails[] leavedetail = LeaveDetails.listById(empId);
    for (LeaveDetails ld : leavedetail) {
      if ((endDate.compareTo(ld.getEndDate())) <= 0 && endDate.compareTo(ld.getStartDate()) >= 0) {
        throw new IllegalArgumentException("--Sorry !! You have already applied leave this day--");
      }
    }
  }
 /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }
 /**
  * @param empId to get the manager's id.
  * @return list of records reporting to that manager
  */
  public static Employee[] isManager(final int empId) {
    List<Employee> es = dao().findManager(empId);
    return es.toArray(new Employee[es.size()]);
  }
  @Override
    public final String toString() {
    return " EmpId:" + empId + " EmpName:" + empName + " EmpEmail:" + empEmail
        + " EmpMobile:" + mobNo + "EmpDateOfJoining:" + doj + "EmpManager:" + managerId
          + " EmpDept:" + dept + "LeaveAvailable:" + avlBalance;
  }
   /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

    /**
* @param strtDate start date.
* @param doj date of joining.
* @return false if data is incorrect.
*/
  public static boolean validateStartDate(final Date strtDate, final Date doj) {
    boolean res1 = false;
    if (!strtDate.after(doj)) {
      res1 = false;
    } else {
      res1 = true;
    }
    return res1;
  }
      /**
* @param strtDate start date.
* @param endDate end date.
* @return false if data is incorrect.
*/
  public static boolean validateEndDate(final Date strtDate, final Date endDate) {
    boolean res2 = false;
    if (!endDate.after(strtDate) && !endDate.equals(strtDate)) {
      res2 = false;
    } else {
      res2 = true;
    }
    return res2;
  }
  /**
* @param strtDate start date of leave.
* @param endDate end date of leave.
* @param noofdays number of days leave required.
* @return the validation of dates.
*/
  public static boolean cal(final Date strtDate, final Date endDate, final int noofdays) {
    long duration = (endDate.getTime() - strtDate.getTime());
    int days = (int) duration / 86400000;
    days += 1;
    boolean trial = false;
    if (noofdays != days) {
      trial = false;
    } else {
      trial = true;
    }
    return trial;
  }
/**
* @param noofdays number of days leave required.
* @param strtDate start date of leave.
* @param endDate end date of leave.
* @param lr end reason of leave.
* @param empId employee id for leave.
* @return to return the result.
*/
  public static final int applyLeave(final int noofdays, final Date strtDate, final Date endDate,
      final String lr,  final int empId) {
    int updateResult = 0;
    int res = LeaveDetails.ldao().applyLeave(noofdays, strtDate, endDate, lr, empId);
    if (res == 1) {
      Employee employee = Employee.listById(empId);
      int newLeaveBal = employee.getAvlBal() - noofdays;
      updateResult = Employee.dao().updateLeaveBal(newLeaveBal, empId);
    }
    return updateResult;
  }
}
