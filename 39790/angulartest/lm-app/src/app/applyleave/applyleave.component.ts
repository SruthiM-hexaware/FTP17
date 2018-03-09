import { Component, OnInit } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import {EmployeeService} from '../employee.service';
import { NgForm } from "@angular/forms";
import {Router} from '@angular/router';


import {Observable} from 'rxjs/Rx';
import {Leave} from '../Leave';




@Component({
  selector: 'app-applyleave',
  templateUrl: './applyleave.component.html',
  styleUrls: ['./applyleave.component.css']
})
export class ApplyleaveComponent implements OnInit {
  empId: number;
  model=new Leave();
  msg:String;
  isValidFormSubmitted = false;

  constructor(private _router:Router, public applyLeaveService:EmployeeService) {
    this.model.empId=parseInt(localStorage.getItem("empId"));
   }

   calculateDays() {
    let date1: string=this.model.startDate.toString();
    let date2: string=this.model.endDate.toString();
    let diffInMs: number = Date.parse(date2) - Date.parse(date1);
    let diffInHours: number = diffInMs / 1000 / 60 / 60/24;
    this.model.noOfDays=diffInHours + 1;
 
    }

    applyLeave(form: NgForm)
    {
        this.isValidFormSubmitted=false;
        if(form.invalid) {
         return; 
      }  
        this.applyLeaveService.applyLeave(this.model).subscribe(
        
            d => {
                this.msg=d;
                alert(this.msg);
                this._router.navigate(['/dashboard']);
            },
            err => { 
                this.msg=err;
                alert(this.msg);
            }
        )
        
    }
    cancel() {
      this._router.navigate(['/dashboard']);
      }     
  ngOnInit() {
  }

}