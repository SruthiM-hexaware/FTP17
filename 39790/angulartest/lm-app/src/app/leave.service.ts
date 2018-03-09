import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { Employee } from './employee';
import { Leave } from './leave';
import { Http, Response } from '@angular/http';

@Injectable()
export class LeaveService {

  constructor(private http: Http) { }
  leaveHis(empId): Observable<Leave[]> {
    return this.http.get('/ftp17/api/LeaveDetails/leavehistory/' + empId).
      map((res: Response) => res.json()).
      catch((error: any) =>
        Observable.throw(error.json().error || 'Not Found'));
  }


  getPendingLeaves(mgid): Observable<Leave[]> {
    return this.http.get("/ftp17/api/LeaveDetails/pendingLeave/" + mgid).
      map((res: Response) => res.json()).
      catch((error: any) => Observable.throw(error.json().error || 'Not Found'))
  }
  approveOrDeny(ld:Leave) : Observable<string> { 
    return this.http.post("/ftp17/api/LeaveDetails/approveorDeny",ld).map(response=>response.text())
.catch((error:any)=>Observable.throw(error.toString() || 'Server Error'));
}
}

