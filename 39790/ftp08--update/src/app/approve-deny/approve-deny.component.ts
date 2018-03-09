import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from './../employee.service';
import { Employee } from "../employee";
import { LeaveDetails } from "../leavedetails";

import { Response } from '@angular/http';


@Component({
  providers: [EmployeeService],
  selector: 'app-approve-deny',
  templateUrl: './approve-deny.component.html',
  styleUrls: ['./approve-deny.component.css']
  
})
export class ApproveDenyComponent implements OnInit {
  leaveDetail;
  employee;
  message:string;
  constructor( private router:Router, private route:ActivatedRoute, public empservice:EmployeeService) { 
    //this.leaveDetail=this.route.snapshot.params['leaveDetail'];
    //this.emp=this.route.snapshot.params['employee'];
    this.employee = localStorage.getItem('emp');
    this.leaveDetail = localStorage.getItem('leaDetail');
    console.log(JSON.stringify(this.employee));
    this.employee=JSON.parse(this.employee);
    this.leaveDetail=JSON.parse(this.leaveDetail);
  }

  doApprove(){
  //console.log(empId);
  console.log(this.leaveDetail.emplId)
  this.empservice.approve(this.leaveDetail.leaId,this.leaveDetail).subscribe(
    data => {
      //this.message="applied";
      alert("Leave is Approved");
      this.router.navigate(['/dashboard']);
    },
    err => {
      this.message="";
      console.log(err);
    });    
  }

  doDeny(){
    //console.log(empId);
    console.log(this.leaveDetail.emplId)
    this.empservice.deny(this.leaveDetail.leaId,this.leaveDetail).subscribe(
      data => {
        this.message='';
        alert("Leave is Denied");
        this.router.navigate(['/dashboard']);
      },
      err => {
        this.message="";
        console.log(err);
      });    
  }

  doCancel(){
      this.router.navigate(['/dashboard']);
    }
  ngOnInit() {
  }

}
