package com.hexaware.ftp17.model;
import java.util.Objects;
import java.util.Date;
import java.util.List;
import com.hexaware.ftp17.persistence.DbConnection;
import com.hexaware.ftp17.persistence.LeaveDAO;




/**
 * LeaveDetails class to store Leave Details .
 * @author hexware
 */

public class LeaveDetails {

  /**
   * leaveId to store Leave id.
   */

  private int leaveId;
  private int noOfDays;
  private Date startDate;
  private Date endDate;
  private LeaveType leaveType;
  private LeaveStatus leaveStatus;
  private String leaveReason;
  private Date leaveApplied;
  private String leaveComments;
  private int empId;
  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }


    LeaveDetails lev = (LeaveDetails) obj;


    if (Objects.equals(leaveId, lev.leaveId) && Objects.equals(noOfDays, lev.noOfDays)
         && Objects.equals(startDate, lev.startDate) && Objects.equals(endDate, lev.endDate)
            && Objects.equals(leaveType, lev.leaveType) && Objects.equals(leaveStatus, lev.leaveStatus)
                && Objects.equals(leaveReason, lev.leaveReason) && Objects.equals(leaveApplied, lev.leaveApplied)
                    && Objects.equals(leaveComments, lev.leaveComments)
                        && Objects.equals(empId, lev.empId)) {
      return true;
    }
    return false;
  }


  @Override
    public final int hashCode() {
    return Objects.hash(leaveId, noOfDays, startDate, endDate, leaveType, leaveStatus,
        leaveReason, leaveApplied, leaveComments, empId);
  }

    /**
   * @param argLeaveId to initialize Leave id.
   */
  public LeaveDetails(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }

  /**
   * @param argLeaveId to initialize Leave id.
   * @param argNoOfDays to initialize Number  of Leave Days.
   * @param argStartDate to initialize Leave Start Date.
   * @param argEndDate to initialize Leave End Date.
   * @param argLeaveType to initialize Type of Leave.
   * @param argLeaveStatus to initialize Status of Leave.
   * @param argLeaveReason to initialize Reason for Leave.
   * @param argLeaveApplied to initialize Leave Applied.
   * @param argLeaveComments to initialize Comments for Leave.
   * @param argEmpId to get the employee id.
  */


  public LeaveDetails(final int argLeaveId, final int argNoOfDays,
      final Date argStartDate, final Date argEndDate, final LeaveType argLeaveType,
        final LeaveStatus argLeaveStatus, final String argLeaveReason,
          final Date argLeaveApplied, final String argLeaveComments,
            final int argEmpId) {

    this.leaveId = argLeaveId;
    this.noOfDays = argNoOfDays;
    this.startDate = argStartDate;
    this.endDate = argEndDate;
    this.leaveType = argLeaveType;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
    this.leaveApplied = argLeaveApplied;
    this.leaveComments = argLeaveComments;
    this.empId = argEmpId;
  }
/**
   * default constructor .
   */

  public LeaveDetails() {
    this.leaveId = 0;
    this.noOfDays = 0;
    this.startDate = null;
    this.endDate = null;
    this.leaveType = null;
    this.leaveStatus = null;
    this.leaveReason = null;
    this.leaveApplied = null;
    this.leaveComments = null;
  }
  /**
   * to get leave id.
   * @return leave id.
   */
  public  final int getLeaveId() {

    return leaveId;
  }
  /**
   * Gets the EmpId.
   * @return this employee Id.
   */

  public final int getEmpId() {
    return empId;
  }

  /**
   * Gets the Number of Leave Days.
   * @return Number of  Leave Days.
   */
  public final int getNoOfDays() {
    return noOfDays;
  }

  /**
   * Gets the Start Date of Leave.
   * @return Start Date of Leave.
   */

  public final Date getStartDate() {
    return startDate;
  }

  /**
   * Gets the End Date of Leave.
   * @return End Date of Leave.
   */

  public final Date getEndDate() {
    return endDate;
  }

    /**
   * Gets the Type of Leave.
   * @return Type of Leave.
   */

  public final LeaveType getLeaveType() {
    return leaveType;
  }

  /**
   * Gets the Status of Leave.
   * @return Status of Leave.
   */

  public final LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }

  /**
   * Gets the Reason for Leave.
   * @return Reason for Leave.
   */

  public final String getLeaveReason() {
    return leaveReason;
  }

  /**
   * Gets the Leave Applied Date.
   * @return Leave Applied Date.
   */

  public final Date getLeaveApplied() {
    return leaveApplied;
  }

  /**
   * Gets the Leave Comments.
   * @return Leave Comments.
   */

  public final String getLeaveComments() {
    return leaveComments;
  }


  /**
   *
   * @param argLeaveId to set Leave Id.
   */

  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }
  /**
   *
   * @param argEmpId to set Employee Id.
   */

  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
   /**
   *
   * @param argNoOfDays to set Number of Days for leave.
   */

  public final void setNoOfDays(final int argNoOfDays) {
    this.noOfDays = argNoOfDays;
  }

   /**
   *
   * @param argStartDate to set Start Date of Leave.
   */

  public final void setStartDate(final Date argStartDate) {
    this.startDate = argStartDate;
  }

  /**
   *
   * @param argEndDate to set End Date of Leave.
   */

  public final void setEndDate(final Date argEndDate) {
    this.endDate = argEndDate;
  }

   /**
   *
   * @param argLeaveType to set Type of Leave Required.
   */

  public final void setLeaveType(final LeaveType argLeaveType) {
    this.leaveType = argLeaveType;
  }

   /**
   *
   * @param argLeaveStatus to set Status of Leave.
   */

  public final void setLeaveStatus(final LeaveStatus argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }

  /**
   *
   * @param argLeaveReason to set Reason for the Leave.
   */

  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

  /**
   *
   * @param argLeaveApplied to set Date of Leave Applied.
   */

  public final void setLeaveApplied(final Date argLeaveApplied) {
    this.leaveApplied = argLeaveApplied;
  }

   /**
   * The dao for leavedetails.
   * @return leave details connection.
   */
  public static LeaveDAO ldao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDAO.class);
  }
  /**
   * list all employee details.
   * @param levId leave ID of employee.
   * @param levStatus leave status by manager
   * @param levComments comments by manager.
   * @param mgrID manager ID.
   * @return status.
   */
  public static boolean approveOrDenyStatus(final int levId, final LeaveStatus levStatus,
                                  final String levComments, final int mgrID) {
    int updateStatus = ldao().update(levId, levStatus.toString(), levComments, mgrID);
    if (updateStatus == 1) {
      System.out.println("The Leave status has been updated");
      return true;
    } else {
      return false;
    }
  }
  /**
   *
   * @param argLeaveComments to set Comments for Leave.
   */


  public final void setLeaveComments(final String argLeaveComments) {
    this.leaveComments = argLeaveComments;
  }
    /**
   * list all employee details.
   * @param empId to specify the ceo employee id.
   */
  public static void autoUpdate(final int empId) {
    ldao().autoUpdate(empId);
  }

  /**
   * list all leave details.
   * @param empId to specify manager's empid.
   * @return all leave details
   */
  public static LeaveDetails[] pendingLeave(final int empId) {

    List<LeaveDetails> ld = ldao().findPendingLeave(empId);
    return ld.toArray(new LeaveDetails[ld.size()]);
  }
 /**
  * leavedetails .
  * @param levId to set leave Id.
  * @param levStatus to set leave status.
  * @param levComments to set leave comments.
  * @param mgrID to set mgrID.
  */

  public static void approveOrDenyBL(final int levId, final LeaveStatus levStatus, final String levComments,
                                      final int mgrID) {
   // int mgrID=1000;
    int avlBalCal = 0;
    LeaveDetails lev = new LeaveDetails();
    lev = LeaveDetails.listId(levId);
    Employee e = Employee.listById(lev.getEmpId());

    if (levStatus == LeaveStatus.APPROVED && lev.getLeaveStatus() == LeaveStatus.PENDING
        || levStatus == LeaveStatus.PENDING && lev.getLeaveStatus() == LeaveStatus.APPROVED) {
      LeaveDetails.approveOrDenyStatus(levId, levStatus, levComments, mgrID);
    } else if (levStatus == LeaveStatus.APPROVED || levStatus == LeaveStatus.PENDING
                 && lev.getLeaveStatus() == LeaveStatus.DENIED) {
      if (LeaveDetails.approveOrDenyStatus(levId, levStatus, levComments, mgrID)) {
        avlBalCal = e.getAvlBal() - lev.getNoOfDays();
        Employee.avlBalanceUpdation(avlBalCal, lev.getEmpId());
      }
    } else if (levStatus == LeaveStatus.DENIED && lev.getLeaveStatus() == LeaveStatus.PENDING
              || levStatus == LeaveStatus.DENIED && lev.getLeaveStatus() == LeaveStatus.APPROVED) {
      if (LeaveDetails.approveOrDenyStatus(levId, levStatus, levComments, mgrID)) {
        avlBalCal = e.getAvlBal() + lev.getNoOfDays();
        Employee.avlBalanceUpdation(avlBalCal, lev.getEmpId());
      }
    } else if (levStatus == LeaveStatus.DENIED && lev.getLeaveStatus() == LeaveStatus.DENIED) {
      System.out.println("IT has been already denied");
    }

  }
/**
   * list all leave details.
   * @param levId to specify leave ID.
   * @return all leave details
   */
  public static LeaveDetails listId(final int levId) {
    return ldao().find(levId);
  }
/**
   * list all leave details.
   * @return leave details.
   */
  public final String toString() {
    return " leaveId:" + leaveId + " noOfDays:" + noOfDays + " startDate:" + startDate
        + " endDate:" + endDate + "leaveType:" + leaveType + "leaveStatus:" + leaveStatus
          + " leaveReason:" + leaveReason + "leaveApplied:" + leaveApplied + " leaveComments:"
            + leaveComments + " empId:" + empId;
  }
   /**
    * list employee history details by id.
    * @param empID id to get employee history details.
    * @return LeaveDetails
    */
  public static LeaveDetails[] listById(final int empID) {
    List<LeaveDetails> es = ldao().findLeaveHistory(empID);
    return es.toArray(new LeaveDetails[es.size()]);
  }
  public static LeaveDetails[] listall() {
    List<LeaveDetails> ld = ldao().listall();
    return ld.toArray(new LeaveDetails[ld.size()]);
    
  }
  public static LeaveDetails[] allPendingLeave() {

    List<LeaveDetails> ld = ldao().findPendingLev();
    return ld.toArray(new LeaveDetails[ld.size()]);
  }
}

