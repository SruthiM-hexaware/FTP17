package com.hexaware.ftp17.util;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import com.hexaware.ftp17.model.Employee;
import com.hexaware.ftp17.model.LeaveDetails;

@Path("/leaveDetails")
public class LeaveDetailsRest {

@GET
@Path("/leaveDetails/{id}")
@Produces(MediaType.APPLICATION_JSON)
public final LeaveDetails[] employeeleavehistory(@PathParam ("id") final int id) {
  final LeaveDetails[] ld = LeaveDetails.listById(id);
  if (ld == null) {
    System.out.println("no leaves applied");
  }
  return ld;
}
@GET
@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
public final LeaveDetails[] employeeleavehistory() {
  final LeaveDetails[] ld = LeaveDetails.listall();
  if (ld == null) {
    System.out.println("no leaves applied");
  }
  return ld;
}
@GET
@Path("/leaveI/{levid}")
@Produces(MediaType.APPLICATION_JSON)
public final LeaveDetails listLeaveid(@PathParam("levid") final int levid ) {
    final LeaveDetails ld = LeaveDetails.listId(levid);
    if(ld == null) {
        throw new NotFoundException("no such leavd id" + levid);
    }
    return ld;

}
@GET
@Path("/pending/{empid}")
@Produces(MediaType.APPLICATION_JSON)
public final LeaveDetails[] pendingLeaves(@PathParam("empid") int empid) {
    final LeaveDetails[] ld = LeaveDetails.pendingLeave(empid);
    if(ld == null) {
        throw new NotFoundException("not a manager");
    }
    return ld;
}
@GET
@Path("/Allpending")
@Produces(MediaType.APPLICATION_JSON)
public final LeaveDetails[] AllPendingleaves() {
    final LeaveDetails[] ld = LeaveDetails.allPendingLeave();
    return ld;
}

}