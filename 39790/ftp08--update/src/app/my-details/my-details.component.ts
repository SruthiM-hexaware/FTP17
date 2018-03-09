import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
@Component({
  selector: 'app-my-details',
  templateUrl: './my-details.component.html',
  styleUrls: ['./my-details.component.css'],
  providers: [ EmployeeService ]
})
export class MyDetailsComponent implements OnInit {
  employee: Employee;
  empId;
  constructor(private employeeService: EmployeeService) { 
    this.employee=new Employee(this.empId);
    this.empId = localStorage.getItem('empId');
  }
 
  ngOnInit() {
   this.getEmployee();
  }

  
  getEmployee(): void {
    this.employeeService.getEmployee(this.empId).then(employee => {
      console.log('getEmployee promise resolved : ' + employee);
      this.employee = employee;
    }
  );
}

}