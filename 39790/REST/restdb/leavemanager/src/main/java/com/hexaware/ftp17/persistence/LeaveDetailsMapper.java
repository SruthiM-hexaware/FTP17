package com.hexaware.ftp17.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.hexaware.ftp17.model.LeaveStatus;
import com.hexaware.ftp17.model.LeaveType;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;


import com.hexaware.ftp17.model.LeaveDetails;
/**
 * Mapper class to map from result set to leavedetails object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
  /**
   * @param idx the index
   * @param rs the resultset
   * @param ctx the context
   * @return the mapped LeaveDetail object
   * @throws SQLException in case there is an error in fetching data from the resultset
   */
  public final LeaveDetails map(final int idx, final ResultSet rs, final StatementContext ctx) throws SQLException {
    /**
     * @return LeaveDetails
     */
    return new LeaveDetails(rs.getInt("LEV_ID"), rs.getInt("LEV_NO_OF_DAYS"),
      rs.getDate("LEV_START_DATE"), rs.getDate("LEV_END_DATE"),
        LeaveType.valueOf(rs.getString("LEV_TYPE")),
          LeaveStatus.valueOf(rs.getString("LEV_STATUS")), rs.getString("LEV_REASON"),
            rs.getDate("LEV_APPLIED_DATE"), rs.getString("LEV_COMMENTS"),
              rs.getInt("EMP_ID"));

  }
}
