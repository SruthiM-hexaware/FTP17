import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';
import { Employee } from './employee';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ EmployeeService ]
})
export class AppComponent implements OnInit {
  constructor(private employeeService: EmployeeService, private _router : Router) { }

  title = 'Leave Management Application';
  
  ngOnInit(): void {
  }
}