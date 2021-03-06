import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './listpending.component.html',
  styleUrls: ['./listpending.component.css']
  
})
export class ListpendingComponent implements OnInit {
  employees;
  leaveDetails;
  selectedRow;
  showButton:boolean = false;
  select:boolean = false;
  leaDetail;
  emp;
  constructor(private employeeService: EmployeeService,  private router:Router) { 
    //this.employee=new Employee(1000);
  }
 
  ngOnInit() {
   this.listPendingEmpDetails();
   this.listPendingLeaveDetails();
  }
  

  
  listPendingEmpDetails(): void {
    this.employeeService.listPendingEmpDetails(1000).then(employees => {
      console.log('employee promise resolved : ' +employees );
      this.employees= employees;
    }
  );
}
  listPendingLeaveDetails(): void {
    this.employeeService.listPendingLeaveDetails(1000).then(leaveDetails => {
      console.log('leavedetails promise resolved : ' +JSON.stringify(leaveDetails) );
      this.leaveDetails= leaveDetails;
    }
  );
}

setClickedRow(leaveDetail, employee) {
  this.selectedRow=leaveDetail.leaId;
  //this.select=true;
  console.log(leaveDetail);
  this.showButton=true;
  this.leaDetail=leaveDetail;
  this.emp=employee;
}

doDeselect(){
  this.showButton=false;
}

doApproveDeny(){
  //console.log(leavedetails);
  console.log(this.leaDetail);
  localStorage.setItem('emp', JSON.stringify(this.emp));
  localStorage.setItem('leaDetail', JSON.stringify(this.leaDetail));
  this.router.navigate(['/approvedeny']);

}


}