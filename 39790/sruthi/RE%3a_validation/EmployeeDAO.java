package com.hexaware.ftp17.persistence;
import com.hexaware.ftp17.model.Employee;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.Date;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import java.util.List;
import com.hexaware.ftp17.model.LeaveStatus;
import com.hexaware.ftp17.model.LeaveType;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * return all the details of all the Leaves.
   * @param noOfDays to insert the number of days.
   * @param startDate to insert the start date.
   * @param endDate to insert the end date.
   * @param leaveType to insert the type of leave.
   * @param leaveStatus to insert status of leave.
   * @param leaveReason to insert leave reason.
   * @param empId to insert the Employee id.
   * @return integer.
   */
  @SqlUpdate("INSERT INTO LEAVE_DETAILS(LEV_NO_OF_DAYS,LEV_START_DATE,LEV_END_DATE,LEV_TYPE,LEV_STATUS,"
        + " LEV_REASON, EMP_ID) VALUES ( :noOfDays, :startDate, :endDate, :leaveType, :leaveStatus,"
            + " :leaveReason, :empId)")
  int applyLeave(@Bind("noOfDays") int noOfDays, @Bind("startDate") Date startDate, @Bind("endDate") Date endDate,
        @Bind("leaveType") LeaveType leaveType, @Bind("leaveStatus") LeaveStatus leaveStatus,
        @Bind("leaveReason") String leaveReason, @Bind("empId") int empId);
//   /**
//   * @param employeeId to update.
//   */
//   @SqlUpdate("UPDATE EMPLOYEE SET"
//             +
//             " EMP_AVL_LEAVE = :newLeaveBal "
//             +
//             " WHERE EMP_ID = :empId")
//   /**
//   * @param newLeaveBal updating Leave Balance.
//   */
//     void leaveBal(@Bind("newLeaveBal") int newLeaveBal, @Bind("employeeId") int employeeId);

//     /**
//     * @param empId the id of the employee.
//     */
//   @SqlUpdate("UPDATE LEAVE_DETAILS SET"
//             +
//             " LEV_STATUS = 'APPROVED' "
//             +
//             " WHERE EMP_ID = :empId")
//     void autoApproved(@Bind("employeeId") int empId);
/**
  * close with no args is used to close the connection.
  */
  void close();
}
