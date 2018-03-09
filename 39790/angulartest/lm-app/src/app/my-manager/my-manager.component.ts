import { Component, OnInit } from '@angular/core';
import{Employee} from '../employee';
import{Observable}from 'rxjs/Rx';
import{EmployeeService} from '../employee.service';


@Component({
  selector: 'app-my-manager',
  templateUrl: './my-manager.component.html',
  styleUrls: ['./my-manager.component.css']
})
export class MyManagerComponent implements OnInit {

  mode = new Employee();
  emp: Observable<Employee>;
  constructor(private _serv : EmployeeService ) { 
    this.mode.empId=parseInt(localStorage.getItem("mId"));
    this.emp=this._serv.getEmployeeDet(this.mode.empId);
  }

  ngOnInit() {
  }

}
