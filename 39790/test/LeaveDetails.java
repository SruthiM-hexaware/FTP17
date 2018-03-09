package com.hexaware.ftp02.model;
import com.hexaware.ftp02.persistence.LeaveDAO;
import com.hexaware.ftp02.persistence.DbConnection;

import java.util.Objects;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class LeaveDetails {

    /**
     * leaveID to store leave id.
     * leaveDays to store leave days.
     * leaveStartDate to store starting date of leave.
     * leaveEndDate to store end date of leave.
     * leaveEmpId is to store employee Id.
     * leaveReason to store Reason for the leave.
     * leaveAppliedOn to store applied date.
     * leaveMgrComment to store manager comment.
     * leavestatus to store leavestatus.
     * leavetype to store leave type.
     */
  private int leaveId;
  private int leaveDays;
  private Date leaveStartDate;
  private Date leaveEndDate;
  private int leaveEmpId;
  private String leaveReason;
  private Date leaveAppliedOn;
  private String leaveMgrComment;
  private LeaveStatus leavestatus;
  private LeaveType leavetype;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LeaveDetails lev = (LeaveDetails) obj;
    if  (Objects.equals(leaveId, lev.leaveId)  && Objects.equals(leaveDays, lev.leaveDays)
        && Objects.equals(leaveStartDate, lev.leaveStartDate) && Objects.equals(leaveEndDate, lev.leaveEndDate)
        && Objects.equals(leaveEmpId, lev.leaveEmpId) && Objects.equals(leaveReason, lev.leaveReason)
        && Objects.equals(leaveAppliedOn, lev.leaveAppliedOn) && Objects.equals(leaveMgrComment, lev.leaveMgrComment)
        && Objects.equals(leavestatus, lev.leavestatus) && Objects.equals(leavetype, lev.leavetype)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveId, leaveDays, leaveStartDate, leaveEndDate, leaveEmpId,
                        leaveReason, leaveAppliedOn, leaveMgrComment, leavestatus, leavetype);
  }
  @Override
  public final String toString() {
    SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
    return "  leaveId: " + leaveId + "  leaveDays: " + leaveDays + "  leaveStartDate: "
              + sf.format(leaveStartDate) + "  leaveEndDate: " + sf.format(leaveEndDate) + "  leaveEmpId: "
              + leaveEmpId + "  leaveReason: " + leaveReason
              + "  leaveAppliedOn: " + sf.format(leaveAppliedOn)
              + "  leaveMgrComment: " + leaveMgrComment
              + "  leavestatus: " + leavestatus + "  leavetype: " + leavetype;
  }
  /**
   * @param argLeaveId to initialize leave id.
   */
  public LeaveDetails(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }
/**
   * Default constructor.
   */
  public LeaveDetails() {
  }

  /**
   * @param argLeaveID to initialize leave id.
   * @param argLeaveDays to initialize leave no of days.
   * @param argLeaveStartDate to initialize leave start date.
   * @param argLeaveEndDate to initialize leave end date.
   * @param argLeaveEmpId to initialize empId.
   * @param argLeaveReason to initialize reason for the leave.
   * @param argLeaveAppliedOn to initialize leave applied date.
   * @param argLeaveMgrComment to initialize manager's comment.
   * @param arglvetype to initialize leave type.
   * @param arglvestatus to initialize leavestatus.
   */
  public LeaveDetails(final int argLeaveID, final int argLeaveDays, final Date argLeaveStartDate,
                      final Date argLeaveEndDate, final LeaveType arglvetype, final LeaveStatus arglvestatus,
                      final int argLeaveEmpId, final String argLeaveReason,
                      final Date argLeaveAppliedOn, final String argLeaveMgrComment) {
    this.leaveId = argLeaveID;
    this.leaveDays = argLeaveDays;
    this.leaveStartDate = new Date(argLeaveStartDate.getTime());
    this.leaveEndDate = new Date(argLeaveEndDate.getTime());
    this.leaveEmpId = argLeaveEmpId;
    this.leaveReason = argLeaveReason;
    this.leaveAppliedOn = new Date(argLeaveAppliedOn.getTime());
    this.leaveMgrComment = argLeaveMgrComment;
    this.leavetype = arglvetype;
    this.leavestatus = arglvestatus;
  }

    /**
     * Gets the LeaveId.
     * @return this leave ID.
     */
  public final int getLeaveId() {
    return leaveId;
  }

    /**
     * set the leaveId.
     * @param argLeaveId to set leave id.
     */
  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }
    /**
     * Gets the no of days of employee.
     * @return this no of days of employee.
     */
  public final int getLeaveDays() {
    return leaveDays;
  }

    /**
     *
     * @param argLeaveDays to set no of days of leave.
     */
  public final void setLeaveDays(final int argLeaveDays) {
    this.leaveDays = argLeaveDays;
  }

    /**
     * Gets the starting date.
     * @return this starting date.
     */
  public final Date getLeaveStartDate() {
    return leaveStartDate;
  }

    /**
     *
     * @param argLeaveStartDate to set the start date.
     */
  public final void setLeaveStartDate(final Date argLeaveStartDate) {
    this.leaveStartDate = argLeaveStartDate;
  }

    /**
     * Gets the leave ending date.
     * @return this leave end date.
     */
  public final Date getLeaveEndDate() {
    return leaveEndDate;
  }

    /**
     *
     * @param argLeaveEndDate to set leave end date.
     */
  public final void setLeaveEndDate(final Date argLeaveEndDate) {
    this.leaveEndDate = argLeaveEndDate;
  }

    /**
     * Gets the emloyee id.
     * @return this employee id.
     */
  public final int getLeaveEmpId() {
    return leaveEmpId;
  }

    /**
     *
     * @param argLeaveEmpId to set leave status.
     */
  public final void setLeaveEmpId(final int argLeaveEmpId) {
    this.leaveEmpId = argLeaveEmpId;
  }

    /**
     * Gets the reason of leave.
     * @return this reason of leave.
     */
  public final String getLeaveReason() {
    return leaveReason;
  }

    /**
     *
     * @param argLeaveReason to set employee joining date.
     */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

    /**
     * Gets the leave applied date.
     * @return this leave applied date.
     */
  public final Date getLeaveAppliedOn() {
    return leaveAppliedOn;
  }

    /**
     *
     * @param argLeaveAppliedOn to set ManagerID.
     */
  public final void setLeaveAppliedOn(final Date argLeaveAppliedOn) {
    this.leaveAppliedOn = argLeaveAppliedOn;
  }

    /**
     * Gets the manager comment.
     * @return this manager comment.
     */
  public final String getLeaveMgrComment() {
    return leaveMgrComment;
  }

    /**
     *
     * @param argLeaveMgrComment to set manager comment.
     */
  public final void setLeaveMgrComment(final String argLeaveMgrComment) {
    this.leaveMgrComment = argLeaveMgrComment;
  }

  /**
   * Gets the leave type.
   * @return the leave type.
   */
  public final LeaveType getLeaveType() {
    return leavetype;
  }

  /**
   *
   * @param argLevtype to set manager comment.
   */
  public final void setLeaveType(final LeaveType argLevtype) {
    this.leavetype = argLevtype;
  }

  /**
   * Gets the leave status.
   * @return the leave status.
   */
  public final LeaveStatus getLeaveStatus() {
    return leavestatus;
  }

  /**
   *
   * @param argsLevStatus to set leave status.
   */
  public final void setLeaveStatus(final LeaveStatus argsLevStatus) {
    this.leavestatus = argsLevStatus;
  }
  /**
   * The dao for leave details.
   */
  private static LeaveDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDAO.class);
  }
  /**
   * list employee's leave details by id.
   * @param empID id to get employee's leave details.
   * @return LeaveDetails
   */
  public static LeaveDetails[] listLeaveDetails(final int empID) {
    List<LeaveDetails> es = dao().find(empID);
    return es.toArray(new LeaveDetails[es.size()]);
  }
 /**
   * To respond to leave request.
   * @param argsLeaveId to set leave Id.
   * @param argsLeaveStatus to set leave status.
   * @param argsEmpId to set emp Id.
   * @param argsLeaveBal to set leave balance.
   * @param argsComment to set manager comment.
   * @return string.
   * @throws IllegalArgumentException to handle argument exception.
   */
  public final String approveDeny(final int argsLeaveId, final String argsLeaveStatus, final int argsEmpId,
                                final int argsLeaveBal, final String argsComment) {
    String s = null;
    if (argsLeaveStatus.equalsIgnoreCase("APPROVED")) {
      if (getLeaveStatus() == LeaveStatus.DENIED) {
        dao().action(argsLeaveId, leavestatus.APPROVED, argsComment);
        s = "Your leave is approved!";
        dao().updateBal(argsLeaveId);
        System.out.println("Request approved");
        System.out.println("Leave Balanace: " + argsLeaveBal);
      } else if (getLeaveStatus() == LeaveStatus.APPROVED) {
        System.out.println("The application is already approved");
      } else {
        dao().action(argsLeaveId, leavestatus.APPROVED, argsComment);
        System.out.println("Approved");
      }
    } else if (argsLeaveStatus.equalsIgnoreCase("DENIED")) {
      if (getLeaveStatus() == LeaveStatus.DENIED) {
        s = "Already Denied";
      } else {
        int leaveBalance = argsLeaveBal + getLeaveDays();
        System.out.println("Leave Balance: " + leaveBalance);
        dao().action(argsLeaveId, leavestatus.DENIED, argsComment);
        dao().updateLeavebal(argsLeaveId);
        System.out.println("Request denied");
      }
    } else if (argsLeaveStatus.equalsIgnoreCase("KEEP PENDING")) {
      if (getLeaveStatus() == LeaveStatus.DENIED) {
        dao().action(argsLeaveId, leavestatus.PENDING, argsComment);
        dao().updateBal(argsLeaveId);
        System.out.println("Done");
        s = "Done";
      } else if (getLeaveStatus() == LeaveStatus.APPROVED) {
        dao().action(argsLeaveId, leavestatus.PENDING, argsComment);
        System.out.println("Done");
        s = "Done";
      } else if (getLeaveStatus() == LeaveStatus.PENDING) {
        System.out.println("The application is already pending.");
      }
    } else {
      System.out.println("Invalid option!");
    }
    return s;
  }
  /**
   * list employee details by id.
   * @param leaveId id to get employee details.
   * @return Employee
   */
  public static LeaveDetails listById(final int leaveId) {
    return dao().findl(leaveId);
  }
  /**
   * list pending leave details.
   * @param empId id to get employee details.
   * @return Leave Details array.
   */
  public static LeaveDetails[] listPendingApplication(final int empId) {
    List<LeaveDetails> l = dao().finds(empId);
    return l.toArray(new LeaveDetails[l.size()]);
  }
  /**
   * list employee details by id.
   * @param leaveEmpId id to get employee details.
   * @return Employee
   */
  public static LeaveDetails listByEmpId(final int leaveEmpId) {
    return dao().date(leaveEmpId);
  }
}
