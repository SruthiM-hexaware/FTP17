import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../Employee';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { Leave } from "../leave";
import { LeaveService } from "../leave.service";

@Component({
  selector: 'app-pending-leaves',
  templateUrl: './pending-leaves.component.html',
  styleUrls: ['./pending-leaves.component.css']
})
export class PendingLeavesComponent implements OnInit {

 
  employ: Observable<Employee[]>;
  mgid: number;
  pLeaves: Observable<Leave[]>;
  details: Leave;
  emp: Employee;
  selectedRow : number;
  setRow : Function;
  button: boolean = true;
  constructor(private empserv: EmployeeService, private _router: Router, private leavServ: LeaveService) {
    
    this.employ = this.empserv.getEmployees();
    this.mgid = parseInt(localStorage.getItem("empId"));
    this.pLeaves = this.leavServ.getPendingLeaves(this.mgid);
    this.setRow = function(index){
      this.selectedRow = index;
  }
  }
  
  setClickedRow(ld,ed) {
    this.button = false;
    this.details = ld;
    this.emp = ed;
    localStorage.setItem("eid", this.emp.empId.toString());
    localStorage.setItem("levId", this.details.leaveId.toString());
    localStorage.setItem("stDate", this.details.startDate.toString());
    localStorage.setItem("enDate", this.details.endDate.toString());
    localStorage.setItem("noDays", this.details.noOfDays.toString());
    localStorage.setItem("leaveReason", this.details.leaveReason);
    localStorage.setItem("leaveType", this.details.leaveType);
    localStorage.setItem("empid", this.emp.managerId.toString());
  }

  ngOnInit() {
  }

  clickButton() {
    this._router.navigate(['approveDeny']);
  }


}
