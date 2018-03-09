package com.hexaware.ftp17.persistence;

import com.hexaware.ftp17.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

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
   * To check whether he's a manager or not.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_MANAGER_ID = :empID")
  @Mapper(EmployeeMapper.class)
  List<Employee> findManager(@Bind("empID") int empID);
  /**
  * @param employeeId to update.
  * @param newLeaveBal to update.
  * @return 1 on updation
  */
  @SqlUpdate("UPDATE EMPLOYEE SET"
      +
      " EMP_AVL_LEAVE = :newLeaveBal "
      +
      " WHERE EMP_ID = :employeeId")
/**
* @param newLeaveBal update leave balance.
* @param employeeId emp id.
*/
int updateLeaveBal(@Bind("newLeaveBal") int newLeaveBal, @Bind("employeeId") int employeeId);

   /**
   * updates the employee table with available leaves.
   * @param empId the id of the employee
   * @param day the id of the employee
   * @return 1 on updation
   */
  @SqlUpdate("update EMPLOYEE set EMP_AVL_LEAVE =:status WHERE EMP_ID = :empID")
  int avlBalUpdate(@Bind("status") int day, @Bind("empID") int empId);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
