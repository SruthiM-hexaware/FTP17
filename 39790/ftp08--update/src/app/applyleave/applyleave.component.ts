import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { Apply} from '../services/app.applyleave.service'
import { EmployeeService } from '../employee.service';
import {LeaveDetails } from '../leavedetails';

@Component({
  selector: 'app-applyleave',
  templateUrl: './applyleave.component.html',
  styleUrls: ['./applyleave.component.css']
})
export class ApplyleaveComponent implements OnInit {
  
  message:String;
  leavedetails:LeaveDetails;
  constructor(public empservice:EmployeeService, public router: Router) { 
    this.message='';
    this.leavedetails=new LeaveDetails();
  }

 doApply(emplId){
    this.empservice.doApply(this.leavedetails).subscribe(
      data => {
        this.message=data;
        alert(this.message);
        this.router.navigate(['/dashboard']);
      },
      err => {
        console.log(err);
      });

  }
  doCancel() {
    this.router.navigate(['/dashboard']);
  }
  ngOnInit() {
  }

}
