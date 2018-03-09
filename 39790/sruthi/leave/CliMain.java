package com.hexaware.ftp17.util;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.hexaware.ftp17.model.Employee;
// import com.hexaware.ftp17.model.LeaveDetails;
// import com.hexaware.ftp17.model.LeaveStatus;
// import com.hexaware.ftp17.model.LeaveType;
import java.text.ParseException;
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
    System.out.println("3. Apply for leave");
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
        applyLeave();
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
      System.out.println(employee);
    }
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println(e);
    }
  }
  private void applyLeave() {
    Date strtDate = null, endDate = null;
    System.out.println(" ENTER THE EMPLOYEE ID ");
    int empId = option.nextInt();
   boolean res1= Employee.validateId(empId);
   if(res1==false)
   {
     System.out.println(" INVALID EMPLOYEE ID ");
   }
   else{
      System.out.println(" ENTER THE NUMBER OF DAYS ");
    int noofdays = option.nextInt();

    boolean res2 = Employee.validateDays(noofdays);
    if(res2==false)
    {
      System.out.println(" YOU HAVE INSUFFICIENT LEAVE BALANCE ");
    }
   else {
    System.out.println(" ENTER START DATE IN THIS FORMAT =>(YYYY-MM-DD) ");
    String sd = option.next();
    System.out.println("Enter End Date in this format =>(YYYY-MM-DD):");
    String ed = option.next();
    boolean res3=Employee.validateDate(ed,sd);
    if(res3==false)
    {
    System.out.println(" INVAILD DATE ENTRY ");
    }
    else{
      boolean res4 = Employee.cal(sd,ed,noofdays);
      if(res4==false)
      {
        System.out.println("INVALID NUMBER OF DAYS");
      }
      else{
        System.out.println(" ENTER THE REASON FOR LEAVE ");
        String reason=option.next();
        Employee.applyLeave(noofdays, strtDate, endDate, lr, empId);
      }
    }
      
   }
 }
   System.out.println("***  YOU HAVE SUCCESSFULLY APPLIED FOR A LEAVE ***");
  }
  /**
   * The main entry point.
   * @param ar the list of arguments`
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
