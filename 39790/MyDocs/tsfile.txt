import { Component, OnInit } from '@angular/core';
import { LeaveDetails} from '../LeaveDetails';
import { Employee } from '../Employee';
import { Route } from '@angular/router/src/config';
import { LeaveDemoService } from '../leave-demo.service';
import { Observable } from 'rxjs/Observable';
import { NgForm } from '@angular/forms/src/directives/ng_form';

@Component({
  selector: 'app-approve-deny',
  templateUrl: './approve-deny.component.html',
  styleUrls: ['./approve-deny.component.css']
})
export class ApproveDenyComponent implements OnInit {
command : string;
mode = new LeaveDetails;
ldet :Observable<string>;
msg:String;

status(command) {

 this.mode.leaveStatus=command;
 alert(this.mode.leaveStatus);
 alert("hi")
}

isValidFormSubmitted = false;
approvrorDeny(form: NgForm)
{
    this.isValidFormSubmitted=false;
    if(form.invalid){
   //     alert("Invalid");
     return; 
  }  
 //   console.log ('end date component' + this.model.leaveEndDate)
    this.lserv.approveOrDeny(this.mode).subscribe(
        d => {
            this.msg=d;
            alert(this.msg);
        },
        err => { 
            this.msg=err;
            alert(this.msg);
            console.log(err);
        }
    )
  
      
}



// approvrorDeny() {
//   this.ldet=this.lserv.approveOrDeny(this.mode);
//   alert("hiiiii " +this.ldet);
// }
  constructor(private lserv : LeaveDemoService) { 
    
  }

  ngOnInit() {
  }



}
