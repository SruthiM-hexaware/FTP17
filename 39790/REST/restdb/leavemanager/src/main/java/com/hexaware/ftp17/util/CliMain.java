package com.hexaware.ftp17.util;

import java.util.Scanner;
import com.hexaware.ftp17.model.LeaveStatus;
import com.hexaware.ftp17.model.Employee;
import com.hexaware.ftp17.model.LeaveDetails;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain  {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply for leave");
    System.out.println("4. Display Leave History");
    System.out.println("5. Display Pending leave");
    System.out.println("6. Approve or Deny");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    int menuOption = 0;
    int flag = 0;
    try {
      menuOption = option.nextInt();
      mainMenuDetails(menuOption);
    } catch (Exception e) {
      System.out.println("Please enter a number between 1 to 7");
      flag = 1;
    }
    if (flag == 1) {
      option.nextLine();
      mainMenu();
    }
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
        listLeaveHistory();
        break;
      case 5:
        listPendingLeave();
        break;
      case 6:
        approveOrDeny();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose between 1 to 7");
    }
    mainMenu();
  }
  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    System.out.println("Employee Id" + "\t" + "Email" + "\t\t\t" + "Employee Name"
         + "\t\t" + "Mobile no" + "\t" + "date of Joining" + "\t"
           + "Manager Id" + "\t" + "Department" + "\t" + "Available leave");
    for (Employee e : employee) {
      System.out.println(e.getEmpId() + "\t\t" + e.getEmpEmail() + "\t\t" + e.getEmpName()
          + "\t\t\t" + e.getMobNo() + "\t" + e.getDoj() + "\t\t" + e.getManagerId() + "\t\t"
            + e.getDept() + "\t" + e.getAvlBal());
    }
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = 0;
    int flag = 0;
    try {
      empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        System.out.println("Employee Id" + "\t" + "Email" + "\t\t\t" + "Employee Name"
            + "\t\t" + "Mobile no" + "\t" + "date of Joining" + "\t\t"
              + "Manager Id" + "\t\t" + "Department" + "\t" + "Available leave");
        System.out.println(employee.getEmpId() + "\t\t" + employee.getEmpEmail() + "\t\t" + employee.getEmpName()
            + "\t\t\t" + employee.getMobNo() + "\t" + employee.getDoj() + "\t\t" + employee.getManagerId() + "\t\t\t"
              + employee.getDept() + "\t\t" + employee.getAvlBal());
      }
    } catch (Exception e) {
      System.out.println("Please enter correct employee id");
      flag = 1;
    }
    if (flag == 1) {
      option.nextLine();
    }
  }
  private void applyLeave() {
    int empId = 0;
    int flag = 0;
    try {
      SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd");
      start.setLenient(false);
      Date strtDate = null, endDate = null;
      System.out.println("ENTER THE EMPLOYEE ID : ");
      empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("\n--Sorry !! No such Employee Found--\n");
      } else {
        int levBal = employee.getAvlBal();
        if (levBal == 0) {
          System.out.println("--Sorry!! You Have Exhausted Your Leaves.--");
        } else {
          System.out.println("LEAVES AVAILABLE = " + levBal);
          System.out.println("ENTER THE NUMBER OF DAYS : ");
          int noofdays = option.nextInt();
          if (levBal < noofdays) {
            System.out.println("\n--You Have Insufficient Leaves--\n");
          } else if (noofdays <= 0) {
            System.out.println("Please Enter a valid Number of Days : ");
          } else {
            System.out.println("ENTER START DATE IN THIS FORMAT =>(YYYY-MM-DD)");
            String sd = option.next();
            strtDate = start.parse(sd);
            Date doj = employee.getDoj();
            boolean res1 = Employee.validateStartDate(strtDate, doj);
            if (!res1) {
              System.out.println("\n--Entered Start Date is Invalid--\n");
            } else {
              Employee.overlapDate(strtDate, empId);
              System.out.println("ENTER END DATE IN THIS FORMAT =>(YYYY-MM-DD):");
              String ed = option.next();
              endDate = start.parse(ed);
              Employee.overlapEndDate(endDate, empId);
              boolean res2 = Employee.validateEndDate(strtDate, endDate);
              if (!res2) {
                System.out.println("\n--Entered End Date is Invalid--\n");
              } else {
                boolean trial = Employee.cal(strtDate, endDate, noofdays);
                if (!trial) {
                  System.out.println("\n--Entered Number of Days is Mis-matched--\n");
                } else {
                  option.nextLine();
                  System.out.println("\nENTER THE REASON FOR LEAVE : \n");
                  String lr = option.nextLine();
                  Employee.applyLeave(noofdays, strtDate, endDate, lr, empId);
                  int mId = employee.getManagerId();
                  if (mId == 0) {
                    LeaveDetails.autoUpdate(empId);
                  }
                }
              }
            }
          }
        }
      }
      System.out.println("\n\n***YOU HAVE SUCCESSFULLY APPLIED FOR LEAVE***\n\n");
    } catch (ParseException e) {
      System.out.println("--Enter the Date in Correct Format--");
      flag = 1;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      flag = 1;
    } catch (InputMismatchException e) {
      System.out.println("--Enter a valid Input--");
      flag = 1;
    }
    if (flag == 1) {
      option.nextLine();
    }
  }
  private void listLeaveHistory() {
    System.out.println("Enter an Employee Id");
    int empId = 0;
    int flag = 0;
    try {
      empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        LeaveDetails[] leaveDetail = LeaveDetails.listById(empId);
        if (leaveDetail.length == 0) {
          System.out.println("Sorry, no leave history is available");
        } else {
          System.out.println("EmployeeID" + "\t" + "LeaveID" + "\t\t" + "NoODays" + "\t\t" + "StartDate" + "\t\t"
              + "EndDate" + "\t\t" + "LeaveTye" + "\t\t" + "LeaveStatus" + "\t\t" + "LeaveReason" + "\t\t"
                + "AppliedDate" + "\t\t" + "Comments");
          for (LeaveDetails lv : leaveDetail) {
            System.out.println(lv.getEmpId() + "\t\t" + lv.getLeaveId() + "\t\t" + lv.getNoOfDays() + "\t\t"
                + lv.getStartDate() + "\t\t" + lv.getEndDate() + "\t\t" + lv.getLeaveType() + "\t\t"
                  + lv.getLeaveStatus() + "\t\t" + lv.getLeaveReason() + "\t\t" + lv.getLeaveApplied() + "\t\t"
                    + lv.getLeaveComments());
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Please enter correct employee id");
      flag = 1;
    }
    if (flag == 1) {
      option.nextLine();
    }
  }
  private void listPendingLeave() {
    System.out.println("Enter the EmployeeId:");
    int empId = 0;
    int flag = 0;
    try {
      empId = option.nextInt();
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        Employee[] lh = Employee.isManager(empId);
        if (lh.length == 0) {
          System.out.println("You have no reporting employees !");
        } else {
          LeaveDetails[] res = LeaveDetails.pendingLeave(empId);
          if (res.length == 0) {
            System.out.println(" ");
            System.out.println("Sorry No Pending Leave Records Found !!");
          } else {
            System.out.println("Leave id" + "\t" + "EmpId" + "\t" + "Leave type"
                + "\t" + "Start Date" + "\t" + "End Date" + "\t" + "noOfDays" + "\t"
                  + "Applied Date" + "\t" + "Leave Status" + "\t" + "Reason" + "\t\t" + "comments");
            for (LeaveDetails ld : res) {
              System.out.println(ld.getLeaveId() + "\t\t" + ld.getEmpId() + "\t" + ld.getLeaveType()
                  + "\t\t" + ld.getStartDate() + "\t" + ld.getEndDate() + "\t" + ld.getNoOfDays() + "\t\t"
                    + ld.getLeaveApplied() + "\t" + ld.getLeaveStatus() + "\t\t" + ld.getLeaveReason()
                      + "\t" + ld.getLeaveComments());
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Please enter correct employee id");
      flag = 1;
    }
    if (flag == 1) {
      option.nextLine();
    }
  }
  private void approveOrDeny() {
    option.nextLine();
    int flag = 0;
    System.out.println("Enter the EmployeeId:");
    int mgrID = 0;
    try {
      mgrID = option.nextInt();
      Employee employee = Employee.listById(mgrID);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        Employee[] lh = Employee.isManager(mgrID);
        if (lh.length == 0) {
          System.out.println("You have no reporting employees !");
        } else {
          LeaveDetails le = new LeaveDetails();
          System.out.println("ENTER THE LEAVE ID ");
          String s = option.next();
          int levId = 0;
          levId = Integer.parseInt(s);
          le = LeaveDetails.listId(levId);
          Employee e1 = Employee.listById(le.getEmpId());
          if (e1.getManagerId() != mgrID) {
            System.out.println(" --PLEASE ENTER A VALID LEAVE ID--");
            approveOrDeny();
          } else {
            System.out.println("ENTER THE LEAVE STATUS ");
            LeaveStatus levStatus = LeaveStatus.valueOf(option.next().toUpperCase());
            System.out.println("ENTER THE COMMENTS ");
            String levComments = option.next();
            LeaveDetails.approveOrDenyBL(levId, levStatus, levComments, mgrID);
          }
        }
      }
    } catch (InputMismatchException  e) {
      System.out.println("ENTER a VALID EMPLOYEE ID");
      flag = 1;
    } catch (NumberFormatException  e) {
      System.out.println("ENTER a VALID LEAVE  ID");
      flag = 1;
    }
    if (flag == 1) {
      approveOrDeny();
    }
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
