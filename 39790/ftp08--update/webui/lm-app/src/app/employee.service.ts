import { Employee } from './employee';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

import { Http } from '@angular/http';
import { Injectable } from '@angular/core';
import {LeaveDetails } from './leavedetails';



@Injectable()
export class EmployeeService {
    //message:String;
    message:Observable<String>;
    constructor(private http: Http) {
    }

   /* doApply(leavedetails:LeaveDetails):Observable<String>{
       return this.http.post('/ftp08/api/employees/applyleave/'+1000,leavedetails)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    
      }*/
    listPendingLeaveDetails(empId) : Promise<any> {
    console.log('listPendingLeaveDetails called on employee.service');
    return this.http.get('http://localhost:8080/ftp08/api/employees/listpendingbyid/'+empId)  
    .toPromise()
    .then(response => response.json())
    .catch(this.handleError);
    }

    listPendingEmpDetails(empId): Promise<any> {
        console.log('listPendingEmpDetails called on employee.service');
        return this.http.get('http://localhost:8080/ftp08/api/employees/listdetailsbypending/'+empId)  
        .toPromise()
        .then(response => response.json())
        .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    approve(emplId,leaveDetail): Observable<String> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/approve/'+leaveDetail.leaId,leaveDetail)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }
    
    deny(emplId,leaveDetail): Observable<String> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/deny/'+leaveDetail.leaId,leaveDetail)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }
}
