package source;

import util.MainClass;

public class Book {
    static int bookId;
    static String bookName;
    static String author;
    static String subject;
    static String title;
    static String publisher;

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public int getBookId(){
        return bookId;
    }

    public void setBookName(String bookName1){
        bookName = bookName1;
    }

    public String getBookName(){
        return bookName;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getPublisher(){
        return publisher;
    }
}

package com.hexaware.ftp08.util;
import java.util.Scanner;

import com.hexaware.ftp08.model.Employee;
import com.hexaware.ftp08.model.LeaveDetails;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply for Leave");
    System.out.println("4. Show leave history");
    System.out.println("5. View pending leave applications");
    System.out.println("6. Approve or Deny Application");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }
  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        apply();
        break;
      case 4:
        showHistory();
        break;
      case 5:
        showPending();
        break;
      case 6:
        approveDeny();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose either 1, 2 or 3");
    }
    mainMenu();
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee.getEmpId() + " " + employee.getEmpName() + " " + employee.getEmpEmail() + " "
              + employee.getEmpMobNo() + " " + employee.getEmpDptName() + " "
              + employee.getEmpMgrId() + " " + employee.getEmpLeaveBalance());
    }
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e.getEmpId() + " " + e.getEmpName() + " " + e.getEmpEmail() + " "
              + e.getEmpMobNo() + " " + e.getEmpDptName() + " "
              + e.getEmpMgrId() + " " + e.getEmpLeaveBalance());
    }
  }

  private void apply() {
    System.out.println("Enter an Employee Id");
    String s = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("Please enter correct id");
      apply();
    }

    Employee employee = Employee.listById(empId);
    int leaveBal = employee.getEmpLeaveBalance();

    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else if (employee.getEmpLeaveBalance() > 0) {
      //System.out.println(employee.getEmpLeaveBalance());
      System.out.println("Enter Start Date in this format =>(yyyy-mm-dd)");
      String startDate = option.next();
      System.out.println("Enter End Date in this format =>(yyyy-mm-dd)");
      String endDate = option.next();
      System.out.println("Enter the Reason");
      String reason1 = option.nextLine();
      String reason2 = option.nextLine();
      String reason = reason1 + reason2;
      System.out.println("Enter the number of Days");
      String s1 = option.next();
      int noOfDays = 0;
      try {
        noOfDays = Integer.parseInt(s1);
      } catch (NumberFormatException e) {
        System.out.println("number of days should be a number");
        apply();
      }
      int newLeaveBal = leaveBal - noOfDays;
      if (employee.getEmpMgrId() == 0 && noOfDays < employee.getEmpLeaveBalance()) {
        Employee.applyLeave(startDate, endDate, reason, empId, noOfDays, newLeaveBal);
      } else if (noOfDays < employee.getEmpLeaveBalance()) {
        Employee.applyLeave(startDate, endDate, reason, empId, noOfDays, newLeaveBal);
      } else {
        System.out.println("Sorry, No sufficient leave Balance available");
      }
    }
  }

  private void showHistory() {
    System.out.println("Enter your emp id");
    String s = option.next();
    int  empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("Please enter correct employee id");
      showHistory();
    }

    Employee employee = Employee.listById(empId);

    if (employee == null) {
      System.out.println("Sorry, No such employee with employee id:" + empId + "exists");
      mainMenu();
    }

    LeaveDetails[] leaDetails = LeaveDetails.listLeaveDetails(empId);

    try {
      if (leaDetails.length == 0) {
        throw new IllegalArgumentException("No leave history is available");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      mainMenu();
    }

    for (LeaveDetails ld : leaDetails) {

      System.out.println(ld.toString());
      System.out.println("-----------------------------------------------------");
    }


  }

  private void showPending() {
    System.out.println("Enter your Id as Manager");
    String s = option.next();
    int  empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("Please enter correct employee id");
      showPending();
    }
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
      mainMenu();
    }
    LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(empId);

    try {
      if (leaDetails.length == 0) {
        throw new IllegalArgumentException("No applications are Pending");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      mainMenu();
    }
    for (LeaveDetails ld : leaDetails) {

      System.out.println(ld.toString());
      System.out.println("-----------------------------------------------------");
    }
  }

  private void approveDeny() {
    System.out.println("Enter your Id as Manager");
    String s = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("Please enter correct manager id");
      approveDeny();
    }


    Employee employee = Employee.listById(empId);
    LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(empId);
    //int leaveBal = employee.getEmpLeaveBalance();
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    }
    try {
      if (leaDetails.length == 0) {
        throw new IllegalArgumentException("No applications are Pending");
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      approveDeny();
    }
    for (LeaveDetails ld : leaDetails) {
      System.out.println(ld.toString());
    }
    System.out.println("1. Approve the application ");
    System.out.println("2. Deny the Application");
    int menuOption = option.nextInt();
    menuDetails(menuOption);

  }

  private void menuDetails(final int menuOption) {
    switch (menuOption) {
      case 1:
        approve();
        break;
      case 2:
        deny();
        break;
      default:
        System.out.println("Choose either 1, 2");
    }

    mainMenu();
  }

  private void approve() {
    System.out.println("Enter the Leave id of the application you want to approve");
    int leaId = option.nextInt();

    LeaveDetails l = LeaveDetails.listByLeaveId(leaId);
    if (l == null) {
      System.out.println("Sorry, No Such Leave Application exists");
    } else {
      System.out.println("Enter the Employee id for that leave id");
      int employeeId = option.nextInt();
      //Employee employee = Employee.listById(employeeId);
      //int leaveBal = employee.getEmpLeaveBalance();
      System.out.println("Enter your comments here");
      String mgrComments1 = option.nextLine();
      String mgrComments2 = option.nextLine();
      String mgrComments = mgrComments1 + mgrComments2;
      //int newLeaveBal = leaveBal - l.getLeaNoOfDays();
      LeaveDetails.approveLeave(mgrComments, leaId, employeeId);
    }
  }

  private void deny() {
    System.out.println("Enter the Leave id of the application you want to deny");
    int leaId = option.nextInt();

    LeaveDetails l = LeaveDetails.listByLeaveId(leaId);
    if (l == null) {
      System.out.println("Sorry, No Such Leave Application exists");
    } else {
      System.out.println("Enter the Employee id for that leave id");
      int employeeId = option.nextInt();
      Employee employee = Employee.listById(employeeId);
      int leaveBal = employee.getEmpLeaveBalance();
      int newLeaveBal = leaveBal + l.getLeaNoOfDays();
      System.out.println("Enter your comments here");
      String mgrComments1 = option.nextLine();
      String mgrComments2 = option.nextLine();
      String mgrComments = mgrComments1 + mgrComments2;
      LeaveDetails.denyLeave(mgrComments, leaId, employeeId, newLeaveBal);
    }


  }

  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}


----------------------------------------------------

package com.hexaware.ftp08.model;

import com.hexaware.ftp08.persistence.DbConnection;
import com.hexaware.ftp08.persistence.EmployeeDAO;

import java.util.Objects;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {

  /**
   * empId to store employee id.
   */
  private int empId;

  /**
   * empName to store employee name.
   */

  private String empName;

  /**
   * empEmail to store employee email.
   */
  private String empEmail;

  /**
   * empMobNo to store employee mobile number.
   */
  private long empMobNo;

   /**
   * empDptName to store employee department name.
   */
  private String empDptName;

   /**
   * empDateJoined to store employee date of joining.
   */
  private String empDateJoined;

   /**
   * empMgrId to store employee manager id.
   */
  private int empMgrId;

   /**
   * empLeaveBalance to store employee leave balance.
   */
  private int empLeaveBalance;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId);
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
   * @param argEmpMobNo to initialize employee mobile number.
   * @param argEmpDptName to initialize employee department name.
   * @param argEmpMgrId to initialize employee manager id.
   * @param argEmpLeaveBalance to initialize employee leave balance.
   */
  public Employee(final int argEmpId, final String argEmpName, final String argEmpEmail,
                  final long argEmpMobNo, final String argEmpDptName,
                  final int argEmpMgrId, final int argEmpLeaveBalance) {
    this.empId = argEmpId;
    this.empName = argEmpName;
    this.empEmail = argEmpEmail;
    this.empMobNo = argEmpMobNo;
    this.empDptName = argEmpDptName;
    this.empMgrId = argEmpMgrId;
    this.empLeaveBalance = argEmpLeaveBalance;

  }

  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * Gets the EmployeeName.
   * @return this Employee's name.
   */
  public final String getEmpName() {
    return empName;
  }

  /**
   *
   * @param argEmpName to set employee name.
   */
  public final void setEmpName(final String argEmpName) {
    this.empName = argEmpName;
  }

  /**
   * Gets the EmployeeEmail.
   * @return this Employee's Email.
   */
  public final String getEmpEmail() {
    return empEmail;
  }

  /**
   *
   * @param argEmpEmail to set employee Email.
   */
  public final void setEmpEmail(final String argEmpEmail) {
    this.empEmail = argEmpEmail;
  }

  /**
   * Gets the EmployeeMobNo.
   * @return this Employee's Mobile number.
   */
  public final long getEmpMobNo() {
    return empMobNo;
  }

  /**
   *
   * @param argEmpMobNo to set employee mobile Number.
   */
  public final void setEmpMobNo(final long argEmpMobNo) {
    this.empMobNo = argEmpMobNo;
  }

  /**
   * Gets the EmployeeDptName.
   * @return this Employee's department Name.
   */
  public final String getEmpDptName() {
    return empDptName;
  }

  /**
   *
   * @param argEmpDptName to set employee Department Name.
   */
  public final void setEmpDptName(final String argEmpDptName) {
    this.empDptName = argEmpDptName;
  }

  /**
   * Gets the EmployeeDateJoined.
   * @return this Employee's Date Joined.
   */
  public final String getEmpDateJoined() {
    return empDateJoined;
  }

  /**
   *
   * @param argEmpDateJoined to set employee DateJoined.
   */
  public final void setEmpDateJoined(final String argEmpDateJoined) {
    this.empDateJoined = argEmpDateJoined;
  }

  /**
   * Gets the EmployeeMgrId.
   * @return this Employee's Manager Id.
   */
  public final int getEmpMgrId() {
    return empMgrId;
  }

  /**
   *
   * @param argEmpMgrId to set employee Manager Id.
   */
  public final void setEmpMgrId(final int argEmpMgrId) {
    this.empMgrId = argEmpMgrId;
  }

  /**
   * Gets the EmployeeLeaveBalance.
   * @return this Employee's Leave Balance.
   */
  public final int getEmpLeaveBalance() {
    return empLeaveBalance;
  }

  /**
   *
   * @param argEmpLeaveBalance to set employee Leave Balance.
   */
  public final void setEmpLeaveBalance(final int argEmpLeaveBalance) {
    this.empLeaveBalance = argEmpLeaveBalance;
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

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

  /**
 * insert employee leave details.
 * @param startDate to get employee details.
 * @param endDate to get employee details.
 * @param reason to get employee reason.
 * @param empId to get employee id.
 * @param days to get leave number of days.
 * appliedDate for inserting applied on Date
 * @param leaveBal to get the leave balance.
 * diff for calculating difference between dates
 * numberOfDays to store number of days
 * @throws ParseException throws Parse Exception
 */
  public static void applyLeave(final String startDate, final String endDate,
                                final String reason, final int empId, final int days, final int leaveBal) {
    try {
      java.util.Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
      java.sql.Date sDate = new java.sql.Date(dt1.getTime());
      java.util.Date dt2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
      java.sql.Date eDate = new java.sql.Date(dt2.getTime());
      long diff = dt2.getTime() - dt1.getTime();
      int numberOfDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
      java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
      if (sDate.after(appliedDate) && eDate.after(appliedDate) && eDate.after(sDate) && days < numberOfDays) {
        dao().insert(sDate, eDate, days, appliedDate, reason, empId);
        dao().leaveBal(leaveBal, empId);
        if (listById(empId).getEmpMgrId() == 0) {
          dao().autoApproved(empId);
        }
      } else {
        System.out.println("Either Start date or End date is incorrect: Please enter correct dates");
      }

    } catch (ParseException e) {
      System.out.println("Please enter date in correct format");
    }
  }

}


------------------------------------------------

package com.hexaware.ftp08.persistence;

import com.hexaware.ftp08.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;
import java.util.Date;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
 * insert all the details of the employee.
 * @param sDate the start date of the employee
 * @param eDate the end date of the employee
 * @param days the number of days of the employee
 * @param appliedDate the applied date of the employee
 * @param reason the reason for applying.
 * @param empId the employee id of the employee
 */
  @SqlUpdate("insert into LEAVE_HISTORY "
             +
             "             (LEA_START_DATE, "
             +
             "              LEA_END_DATE, "
             +
             "              LEA_NO_OF_DAYS, "
             +
             "              LEA_APPLIED_ON, "
             +
             "              LEA_REASON, "
             +
             "              EMP_ID) "
             +
             "values       ( "
             +
             "              :sDate, "
             +
             "              :eDate, "
             +
             "              :days, "
             +
             "              :appliedDate, "
             +
             "              :reason, "
             +
             "              :empId)")
  void insert(@Bind("sDate") Date sDate,
              @Bind("eDate") Date eDate,
              @Bind("days") int days,
              @Bind("appliedDate") Date appliedDate,
              @Bind("reason") String reason,
              @Bind("empId") int empId);

  /**
    * @param newLeaveBal the new leave Balance of the employee.
    * @param employeeId the id of the employee.
    */
  @SqlUpdate("UPDATE EMPLOYEE SET"
            +
            " EMP_LEAVE_BALANCE = :newLeaveBal "
            +
            " WHERE EMP_ID = :employeeId")
    void leaveBal(@Bind("newLeaveBal") int newLeaveBal, @Bind("employeeId") int employeeId);

    /**
    * @param employeeId the id of the employee.
    */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            " LEA_STATUS = 'APPROVED' "
            +
            " WHERE EMP_ID = :employeeId")
    void autoApproved(@Bind("employeeId") int employeeId);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
-----------------------------------------------------------

package com.hexaware.ftp08.model;
import java.util.Objects;
import java.sql.Date;
import java.util.List;
import com.hexaware.ftp08.persistence.DbConnection;
import com.hexaware.ftp08.persistence.LeaveDetailsDAO;
/**
 * Leave Details class to store employee leave details.
 * @author hexware
 */
public class LeaveDetails {
    /**
     * leaId to store leave id.
     */
  private int leaId;
    /**
     * leaStartDate to store leave start date.
     */
  private Date leaStartDate;
    /**
     * leaEndDate to store leave end date.
     */
  private Date leaEndDate;
    /**
     * noOfDays to store number of days.
     */
  private int leaNoOfDays;
    /**
     * leaReason to store employee leave reason.
     */
  private String leaReason;
    /**
     * leaType to store employee leave type.
     */
  private LeaveType leaType;
    /**
     * leaAppliedOn to store employee leave applied on.
     */
  private Date leaAppliedOn;
    /**
     * leaMgrComments to store employee leave manager comments.
     */
  private String leaMgrComments;
    /**
     * leaStatus to store employee leave status.
     */
  private LeaveStatus leaStatus;
    /**
     * leaStatus to store employee leave status.
     */
  private int emplId;
  @Override
  public final boolean equals(final Object ob) {
    if (ob == null) {
      return false;
    }
    if (getClass() != ob.getClass()) {
      return false;
    }
    LeaveDetails lea = (LeaveDetails) ob;
    if (Objects.equals(leaId, lea.leaId)) {
      return true;
    }
    return false;
  }
  @Override
  public final int hashCode() {
    return Objects.hash(leaId);
  }

  @Override
  public final String toString() {
    return "| leave id |" + leaId + "| employee id |" + emplId +  "| start date |" + leaStartDate
            + "| end date |" + leaEndDate + "| number of days |" + leaNoOfDays + "| leave reason |"
            + leaReason + "| leave type |" + leaType + "| leave applied on |" + leaAppliedOn
            + "| manager comments |" + leaMgrComments + "| leave status |" + leaStatus;
  }
  /**
  * @param argLeaId to initialize leave id.
  * @param argLeaStartDate to initialize leave start date.
  * @param argLeaEndDate to initialize leave end date.
  * @param argLeaNoOfDays to initialize leave number of days.
  * @param argLeaReason to initialize leave reason.
  * @param argLeaType to initialize leave type.
  * @param argLeaAppliedOn to initialize leave applied on.
  * @param argLeaMgrComments to initialize leave manager comments.
  * @param argLeaStatus to initialize leave status.
  * @param argEmplId to initialize employee id.
  */
  public LeaveDetails(final int argLeaId, final Date argLeaStartDate, final Date argLeaEndDate,
                        final int argLeaNoOfDays, final String argLeaReason, final LeaveType argLeaType,
                        final Date argLeaAppliedOn, final String argLeaMgrComments,
                        final LeaveStatus argLeaStatus, final int argEmplId) {
    this.leaId = argLeaId;
    this.leaStartDate = argLeaStartDate;
    this.leaEndDate = argLeaEndDate;
    this.leaNoOfDays = argLeaNoOfDays;
    this.leaReason = argLeaReason;
    this.leaType = argLeaType;
    this.leaAppliedOn = argLeaAppliedOn;
    this.leaMgrComments = argLeaMgrComments;
    this.leaStatus = argLeaStatus;
    this.emplId = argEmplId;
  }
  /**
  * The dao for Leave details.
  */
  private static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }
  /**
  * list employee details by id.
  * @param empID id to get leave details.
  * @return LeaveDetails
  */
  /**
  * Gets the Leave Id.
  * @return this Employee's ID.
  */
  public final int getLeaId() {
    return leaId;
  }
  /**
  *
  * @param argLeaId to set leave id.
  */
  public final void setLeaId(final int argLeaId) {
    this.leaId = argLeaId;
  }
  /**
  * Gets the start date.
  * @return this start date.
  */
  public final Date getLeaStartDate() {
    return leaStartDate;
  }
  /**
  *
  * @param argLeaStartDate to set leave start date.
  */
  public final void setLeaStartDate(final Date argLeaStartDate) {
    this.leaStartDate = argLeaStartDate;
  }
  /**
  * Gets the Leave End Date.
  * @return this Leave End Date.
  */
  public final Date getLeaEndDate() {
    return leaEndDate;
  }
  /**
  *
  * @param argLeaEndDate to set leave end date.
  */
  public final void setLeaEndDate(final Date argLeaEndDate) {
    this.leaEndDate = argLeaEndDate;
  }
  /**
  * Gets the Leave Id.
  * @return this Employee's ID.
  */
  public final int getLeaNoOfDays() {
    return leaNoOfDays;
  }
  /**
  *
  * @param argLeaNoOfDays to set leave id.
  */
  public final void setLeaNoOfDays(final int argLeaNoOfDays) {
    this.leaNoOfDays = argLeaNoOfDays;
  }
  /**
  * Gets the Leave Reason.
  * @return this Reason for Leave.
  */
  public final String getLeaReason() {
    return leaReason;
  }
  /**
  *
  * @param argLeaReason to set leave reason.
  */
  public final void setLeaReason(final String argLeaReason) {
    this.leaReason = argLeaReason;
  }
  /**
  * Gets the Leave type.
  * @return this Leave Type.
  */
  public final LeaveType getLeaType() {
    return leaType;
  }
  /**
  *
  * @param argLeaType to set Leave Type.
  */
  public final void setLeaType(final LeaveType argLeaType) {
    this.leaType = argLeaType;
  }
  /**
  * Gets the Leave Applied on.
  * @return this Leave Applied on.
  */
  public final Date getLeaAppliedOn() {
    return leaAppliedOn;
  }
  /**
  *
  * @param argLeaAppliedOn to set Leave Applied on.
  */
  public final void setLeaAppliedOn(final Date argLeaAppliedOn) {
    this.leaAppliedOn = argLeaAppliedOn;
  }
  /**
  * Gets the Manager comments on leave.
  * @return this Manager comments on leave.
  */
  public final String getLeaMgrComments() {
    return leaMgrComments;
  }
  /**
  *
  * @param argLeaMgrComments to set manager comments.
  */
  public final void setLeaMgrComments(final String argLeaMgrComments) {
    this.leaMgrComments = argLeaMgrComments;
  }
  /**
  * Gets the leave status.
  * @return this leave status.
  */
  public final LeaveStatus getLeaStatus() {
    return leaStatus;
  }
  /**
  *
  * @param argLeaStatus to set leave status.
  */
  public final void setLeaStatus(final LeaveStatus argLeaStatus) {
    this.leaStatus = argLeaStatus;
  }

  /**
  * Gets the emplId Id.
  * @return this Employee's ID.
  */
  public final int getEmplId() {
    return emplId;
  }
  /**
  * @param argEmplId to set employee id.
  */
  public final void setEmplId(final int argEmplId) {
    this.emplId = argEmplId;
  }

  /**
  * list all leave details.
  * @param empID for emp id
  * @return all leave details
  */
  public static LeaveDetails[] listLeaveDetails(final int empID) {

    List<LeaveDetails> ls = dao().lis(empID);
    return ls.toArray(new LeaveDetails[ls.size()]);
  }

  /**
  * list all pending details.
  * @param empID for emp id
  * @return all pending details
  */
  public static LeaveDetails[] listPendingApplications(final int empID) {
    List<LeaveDetails> ls2 = dao().finds(empID);
    return ls2.toArray(new LeaveDetails[ls2.size()]);
  }

  /**
  * list leave details by id.
  * @param leaId id to get employee details.
  * @return Employee
  */
  public static LeaveDetails listByLeaveId(final int leaId) {
    return dao().send(leaId);
  }

  /**
  * approve the leave application.
  * @param leaId id to get leave details.
  * @param mgrComments id to update manager comments
  * @param employeeId employee id of the employee
  */
  public static void approveLeave(final String mgrComments, final int leaId, final int employeeId) {
    String status = "APPROVED";
    dao().approve(mgrComments, status, leaId, employeeId);
    //dao().leaveBal(newLeaveBal, employeeId);
  }

  /**
  * deny the leave application.
  * @param leaId id to get leave details.
  * @param mgrComments id to update manager comments.
  * @param employeeId employee id of the employee
  * @param newLeaveBal is new leave balance
  */
  public static void denyLeave(final String mgrComments, final int leaId, final int employeeId, final int newLeaveBal) {
    String status = "DENIED";
    dao().deny(mgrComments, status, leaId, employeeId);
    dao().leaveBal(newLeaveBal, employeeId);
  }
}
--------------------------------------------
package com.hexaware.ftp08.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;
import com.hexaware.ftp08.model.LeaveDetails;

/**
 * The DAO class for employee.
 */
public interface LeaveDetailsDAO {

    /**
     * return all the details of the selected employee.
     *
     * @param empID the id of the employee
     * @return the employee object
     */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE EMP_ID = :empID")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> lis(@Bind("empID") int empID);

  /**
    * return all the details of the selected employee.
    *
    * @param empId the id of the employee
    * @return the employee object
    */
  @SqlQuery("SELECT * "
            +
            "FROM LEAVE_HISTORY "
            +
            "INNER JOIN EMPLOYEE ON EMPLOYEE.EMP_ID = LEAVE_HISTORY.EMP_ID "
            +
            "WHERE "
            +
            "EMPLOYEE.EMP_MGR_ID = :empId "
            +
            "AND "
            +
            "LEAVE_HISTORY.LEA_STATUS = 'PENDING'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> finds(@Bind("empId") int empId);

    /**
     * return all the leave details of the selected employee.
     * @param leaId the id of the employee
     * @return the LeaveDetails object
     */
  @SqlQuery("SELECT * FROM LEAVE_HISTORY WHERE LEA_ID = :leaId")
   @Mapper(LeaveDetailsMapper.class)
    LeaveDetails send(@Bind("leaId") int leaId);

    /**
     * @param mgrComments the id of the employee
     * @param leaId the id of the Leave application
     * @param status is the status of application
     * @param empId the id of the employee
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEA_MGR_COMMENTS = :mgrComments, "
            +
            "    LEA_STATUS = :status"
            +
            "    WHERE LEA_ID = :leaId and EMP_ID = :empId")
    void approve(@Bind("mgrComments") String mgrComments, @Bind("status") String status, @Bind("leaId") int leaId,
                 @Bind("empId") int empId);

    /**
     * @param mgrComments the comments of the manager.
     * @param leaId the id of the Leave application
     * @param status is the status of application
     * @param empId the id of the employee
     */
  @SqlUpdate("UPDATE LEAVE_HISTORY SET"
            +
            "    LEA_MGR_COMMENTS = :mgrComments, "
            +
            "    LEA_STATUS = :status"
            +
            "    WHERE LEA_ID = :leaId and EMP_ID = :empId")
    void deny(@Bind("mgrComments") String mgrComments, @Bind("status") String status, @Bind("leaId") int leaId,
              @Bind("empId") int empId);

    /**
    * @param newLeaveBal the new leave Balance of the employee.
    * @param employeeId the id of the employee.
    */
  @SqlUpdate("UPDATE EMPLOYEE SET"
            +
            " EMP_LEAVE_BALANCE = :newLeaveBal "
            +
            " WHERE EMP_ID = :employeeId")
    void leaveBal(@Bind("newLeaveBal") int newLeaveBal, @Bind("employeeId") int employeeId);
    /**
     * close with no args is used to close the connection.
     */
  void close();
}
--------------------------------
package com.hexaware.ftp08.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.ftp08.model.LeaveDetails;
import com.hexaware.ftp08.model.LeaveStatus;
import com.hexaware.ftp08.model.LeaveType;
/**
 * Mapper class to map from result set to leave details object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
    /**
     * @param idx the index
     * @param rs the resultset
     * @param ctx the context
     * @return the mapped employee object
     * @throws SQLException in case there is an error in fetching data from the resultset
     */
  public final LeaveDetails map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException {

    String s = rs.getString("LEA_TYPE");
    LeaveType lt1 = LeaveType.valueOf(s);

    String s2 = rs.getString("LEA_STATUS");
    LeaveStatus lt2 = LeaveStatus.valueOf(s2);
        /**
         * @return LeaveDetails
         */
    return new LeaveDetails(rs.getInt("LEA_ID"), rs.getDate("LEA_START_DATE"), rs.getDate("LEA_END_DATE"),
                            rs.getInt("LEA_NO_OF_DAYS"), rs.getString("LEA_REASON"),
                            lt1, rs.getDate("LEA_APPLIED_ON"), rs.getString("LEA_MGR_COMMENTS"), lt2,
                            rs.getInt("EMP_ID"));

  }
}
