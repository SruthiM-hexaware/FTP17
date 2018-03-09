package com.hexaware.ftp08.util;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.hexaware.ftp08.model.Employee;
import com.hexaware.ftp08.model.LeaveDetails;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/employees")
public class EmployeeRest {

  /**
   * Returns a list of all the employees.
   * @return a list of all the employees
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Employee[] employeesList() {
    System.out.println("Employees List");
    final Employee[] employees = Employee.listAll();
    return employees;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Employee employeeListById(@PathParam("id") final int id) {
    final Employee empl = Employee.listById(id);
    if (empl == null) {
      throw new NotFoundException("No such Employee ID: " + id);
    }
    return empl;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee leave details
   */
  @GET
  @Path("/leavedetails/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leaveDetailsListById(@PathParam("id") final int id) {
    final LeaveDetails[] leaDetails = LeaveDetails.listLeaveDetails(id);
    return leaDetails;
  }
  /**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the employee details
   */

  @GET
  @Path("/listpending/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] employeeLeaveHistory(@PathParam("id") final int id) {
    final Employee listManagers = Employee.listManager(id);
    final Employee employee = Employee.listById(id);
    if (employee == null) {
      throw new NotFoundException("No such Employee ID: " + id);
    }
    if (listManagers == null) {
      throw new NotFoundException("No such Manager ID: " + id);
    }
    final LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(id);
    return leaDetails;
  }

  /**
   * apply for a leave.
   * @param id the id of the employee
   * @param l instance object of LeaveDetails class
   * @return message which returns String
   */
  @POST
  @Path("/applyleave/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String employeeApplyLeave(@PathParam("id") final int id, final LeaveDetails l) {
    String message = null;

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      message = Employee.check(sdf.format(l.getLeaStartDate()),
                          sdf.format(l.getLeaEndDate()), l.getLeaReason(), id, l.getLeaNoOfDays());
    } catch (Exception e) {
      System.out.println("Enter correct Parsable dates");
    }
    return message;
  }

  /**
   * Returns a list of all the employees.
   * @param leaId the id of the employee
   * @param l is the object of leavedetails class.
   * @return s a list of all the employees
   */
  @POST
  @Path("/approve/{leaId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String approveLeaveStatus(@PathParam("leaId") final int leaId, final LeaveDetails l) {
    final String s = LeaveDetails.approveLeave(l.getLeaMgrComments(), leaId, l.getEmplId());
    return s;
  }
  /**
   * Returns a specific employee's details.
   * @param l is the object of the leavedetails.
   * @param leaId the leaid of the employee
   * @return result a list of all the employees
   */
  @POST
  @Path("deny/{leaId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String denyLeaveStatus(@PathParam("leaId") final int leaId, final LeaveDetails l) {
    final String s = LeaveDetails.denyLeave(l.getLeaMgrComments(), leaId, l.getEmplId());
    return s;
  }

  /**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the employee details
   */

  @GET
  @Path("/listpendingbyid/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final List<LeaveDetails[]> employeeLeaveHistoryDetails(@PathParam("id") final int id) {
    List<LeaveDetails[]> leaveDetailsList = new ArrayList<LeaveDetails[]>();
    final LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(id);
    Set<Integer> empList = new TreeSet<Integer>();
    for (int j = 0; j < leaDetails.length; j++) {
      empList.add(leaDetails[j].getEmplId());
    }

    System.out.println(empList.size());
    Iterator<Integer> itr = empList.iterator();
    while (itr.hasNext()) {
      final LeaveDetails[] leaveDetailsByEmployee = LeaveDetails.listLeaveDetails(itr.next());
      //System.out.println(leaveDetailsByEmployee.length);
      leaveDetailsList.add(leaveDetailsByEmployee);
    }
    System.out.println(leaveDetailsList.size());
    return leaveDetailsList;
  }

  /**
   * Returns a specific employee's leave details.
   * @param id the id of the employee
   * @return the Set of employee details
   */

  @GET
  @Path("/listdetailsbypending/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Set<Employee> employeeDetailsByPending(@PathParam("id") final int id) {
    final LeaveDetails[] leaDetails = LeaveDetails.listPendingApplications(id);
    Set<Employee> a = new HashSet<Employee>();
    for (int i = 0; i < leaDetails.length; i++) {
      Employee employee = Employee.listById(leaDetails[i].getEmplId());
      a.add(employee);
    }
    return a;
  }
}
