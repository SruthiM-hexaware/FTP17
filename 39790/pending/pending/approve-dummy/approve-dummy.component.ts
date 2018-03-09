import { Component, OnInit } from '@angular/core';
import { Employee } from "../Employee";
import { Leave } from "../leave";
import { ActivatedRoute,Router } from '@angular/router';
import {EmpService} from "../emp.service";
import { Observable } from "rxjs/Rx";
import { NgForm } from "@angular/forms";
import { LeaveService } from "../leave.service";

@Component({
  selector: 'app-approve-dummy',
  templateUrl: './approve-dummy.component.html',
  styleUrls: ['./approve-dummy.component.css']
})
export class ApproveDummyComponent implements OnInit {

  emp = new Employee();
  msg : string;
  
  leav = new Leave();
    id : number;
    eno : number;

    empData : Observable<Employee>;
  constructor(private route: ActivatedRoute, private _serv :EmpService, private _lserv : LeaveService) {
    this.id=parseInt(localStorage.getItem("apId"));
    
    alert(this.id);
    this.eno=parseInt(localStorage.getItem("eid")); 
    alert("Employ Data " +this.eno);
    this.empData=this._serv.getEmployee(this.eno); 
    this.leav.leaveId=parseInt(localStorage.getItem("levId"));
    this.leav.startDate=localStorage.getItem("stDate");
    this.leav.endDate=localStorage.getItem("enDate");
    this.leav.noOfDays=parseInt(localStorage.getItem("noDays"));
    this.leav.leaveReason=localStorage.getItem("leaveReason");
    this.leav.leaveType=localStorage.getItem("leaveType");
    this.leav.empId=this.id;
    
    //this.leav.
    alert(this.leav.leaveId);
     //this.emp.empId=this.empData.emp

   }

   approveDeny(form: NgForm)
   {
     alert(this.leav.leaveComments);
     this.leav.leaveStatus="APPROVED";
      
     this._lserv.approveOrDeny(this.leav).subscribe(
      d => {
          this.msg = d;
           alert("Success Message " +this.msg);
      },
      err => {
          this.msg = err;
           alert("Error Msg " +this.msg);
          console.log(err);
      }
  )

     }  
  ngOnInit() {
  }

}
