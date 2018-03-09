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
  constructor(private router:Router, public empservice:EmployeeService) { 
    this.message='';
this.leavedetails=new LeaveDetails();
  }

 doApplyLeave(){
    this.empservice.doApply(this.leavedetails).subscribe(
      data => {
        this.message=data;
      },
      err => {
        console.log(err);
      });

  }


  ngOnInit() {
  }

}
