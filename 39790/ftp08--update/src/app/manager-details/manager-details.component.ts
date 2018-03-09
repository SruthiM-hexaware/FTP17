import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
@Component({
  selector: 'app-manager-details',
  templateUrl: './manager-details.component.html',
  styleUrls: ['./manager-details.component.css'],
  providers: [ EmployeeService ]
})
export class ManagerDetailsComponent implements OnInit {
  employee: Employee;
  empId;
  constructor(private employeeService: EmployeeService) { 
    this.employee=new Employee(this.empId);
    this.empId = localStorage.getItem('empId');
  }
 
  ngOnInit() {
   this.specificManager();
  }

  
  specificManager(): void {
    this.employeeService.specificManager(this.empId).then(employee => {
      console.log('specificManager promise resolved : ' + employee);
      this.employee = employee;
    }
  );
}

}
