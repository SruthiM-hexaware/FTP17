import { Component, OnInit } from '@angular/core';
import {Http,Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {LeaveService} from '../leave.service'
import {Leave} from '../Leave';
import {Router} from '@angular/router';
import {Employee} from '../Employee';
import {EmployeeService} from '../employee.service';
import {login} from '../login';

@Component({
  selector: 'app-leavehistory',
  templateUrl: './leavehistory.component.html',
  styleUrls: ['./leavehistory.component.css']
})
export class LeavehistoryComponent implements OnInit {
  employ : Observable<Employee>;
  model = new Employee();
  leaveDetails : Observable<Leave[]>;
  constructor(private emp : EmployeeService, private lev :LeaveService, private _router : Router) {
    this.model.empId=parseInt(localStorage.getItem("empId"));
    this.leaveDetails = this.lev.leaveHis(this.model.empId);
  }
  apply() {
    this._router.navigate(['/applyleave']);
  }

  ngOnInit() {
  }

}
