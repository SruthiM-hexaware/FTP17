package com.hexaware.ftp17.util;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.hexaware.ftp17.model.Employee;
import com.hexaware.ftp17.model.LeaveDetails;
import com.hexaware.ftp17.model.LeaveStatus;
import com.hexaware.ftp17.model.LeaveType;
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
    System.out.println("4. Exit");
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
      case 4:
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
    System.out.println("---APPLY LEAVE---")
    Date strtDate = null, endDate = null;
    System.out.println("Enter no of Days :");
    int noofdays = option.nextInt();
    if(noofdays<=0)
    {
      System.out.println("Invalid number of days");
    }
    System.out.println("Enter Start Date in this format =>(yyyy-MM-dd)");
    String sd = option.next();
    try {
      SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
      strtDate = start.parse(sd);
    } catch (ParseException e) {
      System.out.println(e);
    }
    System.out.println("Enter end date of leave :");
    String ed = option.next();
    try {
      SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd");
      endDate = end.parse(ed);
      System.out.println(end.parse(ed));
      System.out.println("************************************************************");
      System.out.println(end.format(endDate));
    } catch (ParseException e) {
      System.out.println(e);
    }
    LeaveType lt = LeaveType.EL;
    LeaveStatus ls = LeaveStatus.PENDING;
    System.out.println("Enter the Reason for Leave :");
    String lr = option.next();
    // System.out.println("Enter leave applied on :");
    // String lad = option.next();
    // try {
    //   SimpleDateFormat la = new SimpleDateFormat("yyyy-MM-dd");
    //   appDate = la.parse(lad);
    // } catch (ParseException e) {
    //   System.out.println(e);
    // }
    System.out.println("Enter the EMPLOYEE ID");
    int empid = option.nextInt();

    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee);
    }
    LeaveDetails ldObj = new LeaveDetails(noofdays, strtDate, endDate, lt.EL, ls.PENDING,
                                            lr, /*appDate,*/empid);
    System.out.println(ldObj);
    Employee.applyLeave(ldObj);
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
