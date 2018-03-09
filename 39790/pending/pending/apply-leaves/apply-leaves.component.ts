import { Component, OnInit } from '@angular/core';
// import { Component, OnInit } from '@angular/core';
import { EmpService } from '../emp.service';
import { Employee } from '../Employee';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { Leave } from "../leave";
import { LeaveService } from "../leave.service";

@Component({
  selector: 'app-apply-leaves',
  templateUrl: './apply-leaves.component.html',
  styleUrls: ['./apply-leaves.component.css']
})
export class ApplyLeavesComponent implements OnInit {

  empl: Observable<Employee[]>;
  mgid : number;
  pLeaves : Observable<Leave[]>;
  details : Leave;
  emp : Employee;

  button : boolean = true;
  constructor(private empserv: EmpService, private _router: Router,private leavServ : LeaveService) {
      this.empl = this.empserv.getEmploy();
      this.mgid=parseInt(localStorage.getItem("empId"));
      this.pLeaves=this.leavServ.getPendingLeaves(this.mgid);
    //  alert(this.mgid);
   }

   setClickedRowDup(ld,ed) { 
     this.button=false;
    this.details=ld;
    this.emp=ed;
    localStorage.setItem("eid",this.emp.empId.toString());
    alert("Click "+this.emp.empId.toString());
    localStorage.setItem("levId",this.details.leaveId.toString()); 
    localStorage.setItem("stDate",this.details.startDate.toString());
    localStorage.setItem("enDate",this.details.endDate.toString());
    localStorage.setItem("noDays",this.details.noOfDays.toString());
    localStorage.setItem("leaveReason",this.details.leaveReason); 
    localStorage.setItem("leaveType",this.details.leaveType);
    localStorage.setItem("lobj",ld);
    localStorage.setItem("eobj",ed);
    // alert(this.details.empId + " " +this.details.noOfDays + " " +this.details.leaveReason + " "+this.emp.empName);
     //this.router.navigate(['/Third',this.name]);
    // alert(this.details.empId);
   //  this.router.navigate(['/Third',this.name]);
   localStorage.setItem("empid",this.emp.empId.toString());
    //this._router.navigate(['approveDummy']);
   }

  ngOnInit() {
  }

  clickButton() {
    this._router.navigate(['approveDummy']);
  }

}
