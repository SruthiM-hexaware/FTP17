import { Employee } from './employee';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';
import { Leave } from './Leave';


@Injectable()
export class EmployeeService {
    empId: number;
    constructor(private http: Http) {
        this.empId = parseInt(localStorage.getItem("empId"));
    }
    getEmployees(): Observable<Employee[]> {
        return this.http.get('http://localhost:8080/ftp17/api/employees').
            map((res: Response) => res.json()).
            catch((error: any) =>
                Observable.throw(error.json().error || "Not Found"));
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
    getEmployeeDet(empId): Observable<Employee>//catch block added at hpds 14
    {
        return this.http.get("/ftp17/api/employees/" + empId).map(res =>
            res.json()).catch((error: any) => Observable.throw(error.json().error || 'Not Found'));
    }
    applyLeave(model: Leave): Observable<String> {
        return this.http.post('/ftp17/api/employees/applyforleave/', model)
            .map(response => response.text())
            .catch((error: any) => Observable.throw(error.toString() || 'Server error'));
    }
}
