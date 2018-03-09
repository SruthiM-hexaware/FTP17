import { Component, OnInit } from '@angular/core';
import{Employee} from '../employee';
import{Observable}from 'rxjs/Rx';
import{EmployeeService} from '../employee.service';

@Component({
  selector: 'app-my-details',
  templateUrl: './my-details.component.html',
  styleUrls: ['./my-details.component.css']
})
export class MyDetailsComponent implements OnInit {
  model = new Employee();
  emp: Observable<Employee>;
  constructor(private _serv : EmployeeService ) { 
    this.model.empId=parseInt(localStorage.getItem("empId"));
    this.emp=this._serv.getEmployeeDet(this.model.empId);
  }

  ngOnInit() {
  }

}
