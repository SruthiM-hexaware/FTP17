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
            "LEAVE_HISTORY.LEA_STATUS IN ('PENDING','DENIED','APPROVED')")
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
