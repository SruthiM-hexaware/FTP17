import { Component, OnInit } from '@angular/core';
import {Employee} from '../employee';
import {EmployeeService} from "../employee.service";
import {Router} from '@angular/router';
import {Response} from '@angular/http';
import {LeaveDetails} from '../leavedetails';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  employee:Employee;

  constructor(private employeeService: EmployeeService,private router: Router) { }
  
    title = 'Leave Management Application';
    employees: Employee[];
    
  
    getEmployees(): void {
        this.employeeService.getEmployees().then(employees => {
          console.log('getEmployees promise resolved : ' + employees.length);
          this.employees = employees;
        }
      );
    
    }
    login(empId): void{
      this.router.navigate(['/login']);
      localStorage.setItem('empId', empId);
      
    }
  
    ngOnInit(): void {
      this.getEmployees();
    }
  }