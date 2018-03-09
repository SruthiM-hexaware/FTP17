import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../employee';
import {Router} from '@angular/router';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  employees: Observable<Employee[]>;
  title = 'Leave Management Application';
  constructor(private employeeServ: EmployeeService, private _router : Router) {
    this.employees=this.employeeServ.getEmployees();
  }
  show(eid,mgrid) { 
    localStorage.setItem("empId",eid);
    localStorage.setItem("mId",mgrid);
    this._router.navigate(['/login']);
  }
  ngOnInit() {
  }

}
