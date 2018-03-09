package com.hexaware.ftp17.persistence;

import com.hexaware.ftp17.model.LeaveDetails;
import org.skife.jdbi.v2.sqlobject.Bind;
import java.util.Date;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;
/**
 * The DAO class for employee.
 */
public interface LeaveDAO {
    /**
   * return all the details of all the Leaves.
   * @param noOfDays to insert the number of days.
   * @param startDate to insert the start date.
   * @param endDate to insert the end date.
   * @param leaveReason to insert leave reason.
   * @param empId to insert the Employee id.
   * @return integer.
   */
  @SqlUpdate("INSERT INTO LEAVE_DETAILS(LEV_NO_OF_DAYS,LEV_START_DATE,LEV_END_DATE,"
        + " LEV_REASON, EMP_ID) VALUES ( :noOfDays, :startDate, :endDate, :leaveReason, :empId)")
  int applyLeave(@Bind("noOfDays") int noOfDays, @Bind("startDate") Date startDate, @Bind("endDate") Date endDate,
        @Bind("leaveReason") String leaveReason, @Bind("empId") int empId);
  /**
  * @param empId to update leave status.
  */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET" + " LEV_STATUS = 'APPROVED' " + " WHERE EMP_ID = :empId")
  /**
  * @param empId for emp id.
  */
    void autoUpdate(@Bind("empId") int empId);

    /**
   * return all the details of the selected employee.
   * @param empId the id of the manager
   * @return the leave object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID = :empId")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findLeaveHistory(@Bind("empId") int empId);
 /**
  *updates the leave details with the status.
  *@param levId leave id of the employee.
  *@param comm comments by manager.
  *@param status leave status by manager.
  *@return returns 1 on update.
  *@param mgrID for managermgrID.
  */
  @SqlUpdate("update LEAVE_DETAILS set LEV_STATUS =:status,"
               + "LEV_COMMENTS =:comm where LEV_ID = :levId and emp_id in "
                + "(select Emp_ID from Employee where emp_Manager_id =:mgrID)")
  int update(@Bind("levId") int levId, @Bind("status")
      String status, @Bind("comm") String comm, @Bind("mgrID") int mgrID);
 /**
  *@param levId leave id of the employee
  *@return leavedetail object
  */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE  LEV_ID = :levId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails find(@Bind("levId") int levId);
    /**
  /**
   * return all the details of the selected employee.
   * @param empId the id of the manager
   * @return the leave object
   */
  @SqlQuery("select l.* from leave_details l, employee e"
      + " where l.lev_status = 'PENDING' and e.emp_manager_id= :empId and e.emp_id = l.emp_id")
        //+ "order by l.lev_start_date")

  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findPendingLeave(@Bind("empId") int empId);



  @SqlQuery("SELECT * FROM LEAVE_DETAILS")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> listall();

  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE lEV_STATUS = 'PENDING'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> findPendingLev();

  /**
  * close with no args is used to close the connection.
  */
  /**
  * close with no args is used to close the connection.
  */
  void close();
}
