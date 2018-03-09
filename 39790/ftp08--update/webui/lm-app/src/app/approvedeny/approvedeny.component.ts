import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-approvedeny',
  templateUrl: './approvedeny.component.html',
  styleUrls: ['./approvedeny.component.css']
})
export class ApprovedenyComponent implements OnInit {
  leaveDetail;
  employee;
  message:String;
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
      this.message="";
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
        this.message="";
      },
      err => {
        this.message="";
        console.log(err);
      });    
  }
  ngOnInit() {
  }

}
