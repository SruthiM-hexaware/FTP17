package com.hexaware.ftp08.util;
import java.util.Scanner;

import com.hexaware.ftp08.model.Employee;
import com.hexaware.ftp08.model.LeaveDetails;
import java.text.SimpleDateFormat;
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
    System.out.println("3. Apply for Leave");
    System.out.println("4. Show leave history");
    System.out.println("5. View pending and denied leave applications");
    System.out.println("6. Approve or Deny Application");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    String s = option.next();
    int menuOption = 0;
    try {
      menuOption = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("---------------------------------");
      System.out.println("Please enter a number 1, 2, 3....");
      System.out.println("---------------------------------");
      mainMenu();
    }
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
    String s = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("--------------------------------");
      System.out.println("Please enter correct employee id");
      System.out.println("--------------------------------");
      listEmployeeDetail();
    }
    //int empId = option.nextInt();
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

    if (employee == null) {
      System.out.println("Sorry, No such employee");
    }

    String startDate = enterStartDate();
    String endDate = enterEndDate();
    System.out.println("Enter the number of Days");
    String s3 = option.next();
    int noOfDays = 0;
    try {
      noOfDays = Integer.parseInt(s3);
    } catch (NumberFormatException e) {
      System.out.println("number of days should be a number");
      apply();
    }
    System.out.println("Enter the Reason");
    String reason1 = option.nextLine();
    String reason2 = option.nextLine();
    String reason = reason1 + reason2;
    Employee.check(startDate, endDate, reason, empId, noOfDays);
  }

  private String enterStartDate() {
    System.out.println("Enter Start Date in this format =>(yyyy/mm/dd)");
    String startDate = option.next();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    try {
      sdf.setLenient(false);
      sdf.parse(startDate);
    } catch (ParseException e) {
      System.out.println("Invalid start date");
      enterStartDate();
    }
    return startDate;
  }

  private String enterEndDate() {
    System.out.println("Enter End Date in this format =>(yyyy/mm/dd)");
    String endDate = option.next();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    try {
      sdf.setLenient(false);
      sdf.parse(endDate);
    } catch (ParseException e) {
      System.out.println("Invalid start date");
      enterEndDate();
    }
    return endDate;
  }

  private void showHistory() {
    System.out.println("Enter your emp id");
    String s = option.next();
    int  empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("--------------------------------");
      System.out.println("Please enter correct employee id");
      System.out.println("--------------------------------");
      showHistory();
    }

    Employee employee = Employee.listById(empId);

    if (employee == null) {
      System.out.println("--------------------------------------------------------------");
      System.out.println("Sorry, No such employee with employee id: " + empId + " exists");
      System.out.println("--------------------------------------------------------------");
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
      System.out.println("--------------------------------");
      System.out.println("Please enter correct employee id");
      System.out.println("--------------------------------");
      showPending();
    }
    Employee listManagers = Employee.listManager(empId);
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("--------------------------------------------------------------");
      System.out.println("Sorry, No such employee with employee id: " + empId + " exists");
      System.out.println("--------------------------------------------------------------");
      mainMenu();
    }


    if (listManagers == null) {
      System.out.println("---------------------");
      System.out.println("You are not a manager");
      System.out.println("---------------------");
      mainMenu();
    }

    LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(empId);

    try {
      if (leaDetails.length == 0) {
        throw new IllegalArgumentException("No applications are Pending");
      } else {
        for (LeaveDetails ld : leaDetails) {
          if (ld.getEmplId() != empId) {
            System.out.println(ld.toString());
            System.out.println("-----------------------------------------------------");
          }
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      mainMenu();
    }
  }

  private void approveDeny() {
    System.out.println("Enter your Id as Manager");
    String s = option.next();
    int empId = 0;
    try {
      empId = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("-------------------------------");
      System.out.println("Please enter correct manager id");
      System.out.println("-------------------------------");
      approveDeny();
    }


    Employee employee = Employee.listById(empId);
    LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(empId);
    //int leaveBal = employee.getEmpLeaveBalance();
    if (employee == null) {
      System.out.println("--------------------------------------------------------------");
      System.out.println("Sorry, No such employee with employee id: " + empId + " exists");
      System.out.println("--------------------------------------------------------------");
      mainMenu();
    }

    Employee listManagers = Employee.listManager(empId);
    if (listManagers == null) {
      System.out.println("---------------------");
      System.out.println("You are not a manager");
      System.out.println("---------------------");
      approveDeny();
    }

    try {
      if (leaDetails.length == 0) {
        throw new IllegalArgumentException("No applications are Pending");
      } else {
        for (LeaveDetails ld : leaDetails) {
          if (ld.getEmplId() != empId) {
            System.out.println(ld.toString());
            System.out.println("-----------------------------------------------------");
          }
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      mainMenu();
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
      System.out.println("---------------------------------------");
      System.out.println("Sorry, No Such Leave Application exists");
      System.out.println("---------------------------------------");
    } else {
      System.out.println("Enter the Employee id for that leave id");
      int employeeId = option.nextInt();
      Employee employee = Employee.listById(employeeId);
      if (employee == null) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Sorry, No such employee with employee id: " + employeeId + " exists");
        System.out.println("--------------------------------------------------------------");
        mainMenu();
      }
      //Employee employee = Employee.listById(employeeId);
      //int leaveBal = employee.getEmpLeaveBalance();
      System.out.println("Enter your comments here");
      String mgrComments1 = option.nextLine();
      String mgrComments2 = option.nextLine();
      String mgrComments = mgrComments1 + mgrComments2;
      //int newLeaveBal = leaveBal - l.getLeaNoOfDays();
      LeaveDetails.approveLeave(mgrComments, leaId, employeeId);
      System.out.println("---------------------------------------------------------------------");
      System.out.println("The application of leave id: |" + leaId + "| is successfully approved");
      System.out.println("---------------------------------------------------------------------");
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
      //int employeeId = option.nextInt();
      //Employee employee = Employee.listById(employeeId);
      if (employee == null) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Sorry, No such employee with employee id: " + employeeId + " exists");
        System.out.println("-------------------------------------------------------------------");
        mainMenu();
      }
      //int leaveBal = employee.getEmpLeaveBalance();
      //int newLeaveBal = leaveBal + l.getLeaNoOfDays();
      System.out.println("Enter your comments here");
      String mgrComments1 = option.nextLine();
      String mgrComments2 = option.nextLine();
      String mgrComments = mgrComments1 + mgrComments2;
      LeaveDetails.denyLeave(mgrComments, leaId, employeeId);
      System.out.println("-------------------------------------------------------------------");
      System.out.println("The application of leave id: |" + leaId + "| is successfully denied");
      System.out.println("-------------------------------------------------------------------");
    }


  }
  /**
   * The callMain method.
   */
  public static final void callMain() {
    final CliMain c = new CliMain();
    c.mainMenu();
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
