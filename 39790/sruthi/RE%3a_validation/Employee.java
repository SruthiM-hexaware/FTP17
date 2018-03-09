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
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
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
@param ld used to pass.
*/
  public static final void applyLeave(final LeaveDetails ld) {
    dao().applyLeave(ld.getNoOfDays(), ld.getStartDate(), ld.getEndDate(), ld.getLeaveType(), ld.getLeaveStatus(),
        ld.getLeaveReason(), ld.getEmpId());
  }
}
