import { Injectable } from '@angular/core';
import {Http,Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import{LeaveDetails} from './LeaveDetails';

@Injectable()
export class LeaveDemoService {

  constructor(private http :Http) { }
  getLeavehistory(empid) : Observable<LeaveDetails[]> {
    return this.http.get("http://localhost:8080/ftp17/api/LeaveDetails/leavehistory/"+empid).map((res : Response) => res.json()).catch((error:any)=>Observable.throw(error.json().error || 'not found'));
    }
    getPendingLeaves(empid) : Observable<LeaveDetails[]> {
      return this.http.get("http://localhost:8080/ftp17/api/LeaveDetails/leavehistory/"+empid).map((res : Response) => res.json()).catch((error:any)=>Observable.throw(error.json().error || 'not found'));
      }
      // applyLeave(model:LeaveDetails):Observable<String>{
      //   return this.http.post('http://localhost:8080/ftp02/api/employees/'+this.empaId+'/applyLeave/',model)
      //    .map(response=>response.text())
      //    .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
      //  }

    approveOrDeny(ld:LeaveDetails) : Observable<string> { 
      alert(ld.leaveId + " " +ld.empId + " " +ld.leaveStatus + " " +ld.leaveComments);
        return this.http.post("http://localhost:8080/ftp17/api/LeaveDetails/approveorDeny",ld).map(response=>response.text())
        .catch((error:any)=>Observable.throw(error.toString() || 'Server Error'));
        }
}
