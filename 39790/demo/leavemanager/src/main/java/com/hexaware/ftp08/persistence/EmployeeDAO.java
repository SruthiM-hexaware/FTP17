package com.hexaware.ftp08.persistence;

import com.hexaware.ftp08.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

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
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list1();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * return all the details of the selected employee.
   * @param empNAME the name of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_NAME = :empNAME")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empNAME") String empNAME);

  /**
   * insert all the details of the selected employee.
   * @param empId the id of the employee.
   * @param empNAME the name of the employee.
   * @param empEMAIL the email of the employee.
   * @param empMOBNO the mobile number of the employee.
   * @param empDATEJOINED the date joined of the employee
   * @return the integer value
   */
  @SqlUpdate("INSERT INTO EMPLOYEE2 (EMP_ID, EMP_EMAIL, EMP_MOB_NO,EMP_DATE_JOINED) VALUES (:empID, :empNAME,:empEMAIL, :empMOBNO, :empDATEJOINED)")
  int insert(@Bind("EMP_ID") int empId, @Bind("EMP_NAME") String empNAME, @Bind("EMP_EMAIL") String empEMAIL,
             @Bind("EMP_MOB_NO") long empMOBNO, @Bind("EMP_DATE_JOINED") String empDATEJOINED);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
