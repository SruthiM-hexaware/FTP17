import { Employee } from './employee';
import { LeaveDetails} from './leaveDetails';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';
import { Http } from '@angular/http';
import { Injectable } from '@angular/core';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';


@Injectable()
export class EmployeeService {
    message:Observable<string>;
    empId;
    constructor(private http: Http) {
        this.empId = localStorage.getItem('empId');
    }

    specificManager(empId): Promise<Employee> {
        console.log('specificManager called on employee.service');
        return this.http.get('http://localhost:8080/ftp08/api/employees/specificmanager/'+empId)  
        .toPromise()
        .then(response => response.json() as Employee)
        .catch(this.handleError);
    }

    doApprove(leavedetails:LeaveDetails): Observable<LeaveDetails[]> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/approve/'+7,leavedetails)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }
    doDeny(leavedetails:LeaveDetails): Observable<LeaveDetails[]> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/deny/'+7,leavedetails)
        .map(response=>response.json())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }
    getLeaveDetails(leaId :number, empMgrId: number):Observable<LeaveDetails> {
        console.log('getEmployees called on employee.service');
        
        return this.http.get('http://localhost:8080/ftp08/api/employees/leavedetails/'+leaId+'/'+empMgrId)
        .map(response => response.json()  as LeaveDetails)

    }
    getEmployee(empId : number): Promise<Employee> {
        console.log('getEmployees called on employee.service');
        return this.http.get('http://localhost:8080/ftp08/api/employees/'+empId)
        .toPromise()
        .then(response => response.json() as Employee)
        .catch(this.handleError);
      
    } 
    getEmployees(): Promise<Employee[]> {
        console.log('getEmployees called on employee.service');
        return this.http.get('http://localhost:8080/ftp08/api/employees')
        .toPromise()
        .then(response => response.json() as Employee[])
        .catch(this.handleError);
    }

    
    doLogin(data):Observable<Employee>{
        return this.http.get('http://localhost:8080/ftp08/api/employees/'+data)
         .map(response=>response.json())
         .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
     
       } 
       doMyleave(empId:number): Promise<LeaveDetails[]> {
        console.log('getdoMyleave called on employee.service');
        return this.http.get('http://localhost:8080/ftp08/api/employees/leavedetails/'+empId)
        .toPromise()
        .then(response => {
            console.log(response.json());
            return response.json() as LeaveDetails[]})
        .catch(this.handleError);
    }

    doApply(leavedetails):Observable<String>{
       return this.http.post('http://localhost:8080/ftp08/api/employees/applyleave/'+this.empId,leavedetails)
        .map(response=>response.text())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    
      }

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

    approve(emplId,leaveDetail): Observable<String> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/approve/'+leaveDetail.leaId,leaveDetail)
        .map(response=>response.text())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }
    
    deny(emplId,leaveDetail): Observable<String> {
        console.log('getEmployees called on employee.service');
        return this.http.post('http://localhost:8080/ftp08/api/employees/deny/'+leaveDetail.leaId,leaveDetail)
        .map(response=>response.text())
        .catch((error:any) => Observable.throw(error.toString() || 'Server error'));
    }


    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
