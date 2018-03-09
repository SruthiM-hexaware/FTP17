package com.hexaware.ftp17.model;
import java.util.Objects;
import java.util.Date;
// import com.hexaware.ftp17.persistence.EmployeeDAO;
// import com.hexaware.ftp17.persistence.DbConnection;



/**
 * LeaveDetails class to store Leave Details .
 * @author hexware
 */

public class LeaveDetails {

  /**
   * leaveId to store Leave id.
   */

  private String leaveId;
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
                    && Objects.equals(leaveComments, lev.leaveComments) && Objects.equals(empId, lev.empId)) {
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
   * @param argNoOfDays to initialize Number  of Leave Days.
   * @param argStartDate to initialize Leave Start Date.
   * @param argEndDate to initialize Leave End Date.
   * @param argLeaveType to initialize Type of Leave.
   * @param argLeaveStatus to initialize Status of Leave.
   * @param argLeaveReason to initialize Reason for Leave.
   * @param argLeaveApplied to initialize Leave Applied.
   * @param argLeaveComments to initialize Comments for Leave.
   * @param argEmpId to initialize employee id.

  */

  public LeaveDetails(final String argLeaveId, final int argNoOfDays, final Date argStartDate,
      final Date argEndDate, final LeaveType argLeaveType, final LeaveStatus argLeaveStatus,
        final String argLeaveReason, final Date argLeaveApplied, final String argLeaveComments, final int argEmpId) {
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
   * @param noofdays to initialize Number  of Leave Days.
   * @param sd to initialize Leave Start Date.
   * @param ed to initialize Leave End Date.
   * @param lt to initialize Type of Leave.
   * @param ls to initialize Status of Leave.
   * @param lr to initialize Reason for Leave.
   * @param empid to initialize employee id.

  */
  public LeaveDetails(final int noofdays, final Date sd, final Date ed, final LeaveType lt,
                        final LeaveStatus ls, final String lr, final int empid) {
    this.noOfDays = noofdays;
    this.startDate = sd;
    this.endDate = ed;
    this.leaveType = lt;
    this.leaveStatus = ls;
    this.leaveReason = lr;
    // this.leaveApplied = lad;
    this.empId = empid;
  }

  /**
   * Gets the LeaveId.
   * @return this Leave Id.
   */

  public final String getleaveId() {
    return leaveId;
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
  *Gets the employee id.
  * @return empId.
  */
  public final int getEmpId() {
    return empId;
  }


  /**
   *
   * @param argLeaveId to set Leave Id.
   */

  public final void setLeaveId(final String argLeaveId) {
    this.leaveId = argLeaveId;
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
   *
   * @param argLeaveComments to set Comments for Leave.
   */

  public final void setLeaveComments(final String argLeaveComments) {
    this.leaveComments = argLeaveComments;
  }
  /**
  * @param argEmpId to use the employee id.
  */
  public final void setempId(final int argEmpId) {
    this.empId = argEmpId;
  }

  // private static EmployeeDAO dao() {
  //   DbConnection db = new DbConnection();
  //   return db.getConnect().onDemand(EmployeeDAO.class);
  // }

}
