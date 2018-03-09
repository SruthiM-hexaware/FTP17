import { Component, OnInit } from '@angular/core';
import { Leave } from '../Leave';
import { Employee } from '../Employee';
import { Route } from '@angular/router';
import { LeaveService } from '../leave.service';
import { Observable } from 'rxjs/Observable';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-approve-deny',
  templateUrl: './approve-deny.component.html',
  styleUrls: ['./approve-deny.component.css']
})
export class ApproveDenyComponent implements OnInit {
  command: string;
  mode = new Leave;
  ldet: Observable<string>;
  msg: String;
  startDate: string;
  endDate: string
  status(command) {
    this.mode.leaveStatus = command;
    
  }
  cancel() {
    this._route.navigate(['/dashboard'])
  }
  isValidFormSubmitted = false;
  approvrorDeny(form: NgForm) {
    this.isValidFormSubmitted = false;
    if (form.invalid) {
      return;
    }
    this._lserv.approveOrDeny(this.mode).subscribe(
      d => {
        this.msg = d;
      },
      err => {
        this.msg = err;
        console.log(err);
      }
    )
    this.isValidFormSubmitted = true;
    setTimeout(() => {
      
        this._route.navigate(['/dashboard'])
      }
      , 500);
  }
  empData: Observable<Employee>;
  eno: number;

  id: number;
  constructor(private _lserv: LeaveService, private _route: Router, private _empserv: EmployeeService) {

    this.id = parseInt(localStorage.getItem("empid"));
    this.eno = parseInt(localStorage.getItem("eid"));
    this.empData = this._empserv.getEmployeeDet(this.eno);
    this.mode.leaveId = parseInt(localStorage.getItem("levId"));
    this.startDate = localStorage.getItem("stDate");
    this.endDate = localStorage.getItem("enDate");
    this.mode.noOfDays = parseInt(localStorage.getItem("noDays"));
    this.mode.leaveReason = localStorage.getItem("leaveReason");
    this.mode.leaveType = localStorage.getItem("leaveType");
    this.mode.empId = this.id;



  }

  ngOnInit() {
  }



}


