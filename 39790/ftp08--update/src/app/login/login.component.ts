import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from './../employee.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Response } from '@angular/http';
import { Employee } from '../employee';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title = 'Login';
  empId : string;
  responseMessage : string;
  loginForm : FormGroup;
  employee : Employee;


  constructor(private employeeService: EmployeeService, private router : Router,
              private formBuilder: FormBuilder) { 
    this.empId=null;
    this.employee= new Employee(this.empId);
    this.responseMessage='';
    this.empId=localStorage.getItem('empId');
    
  }

  ngOnInit() {
    
   this.loginForm = this.formBuilder.group({
     empId:['',Validators.compose([Validators.required,Validators.minLength(4)])]
   });
  }

 docancel() {
  this.router.navigate(['/home']);
 }
  doLogin() {
    var formdata=JSON.stringify(this.loginForm.value);
    var data=JSON.parse(formdata);
    this.employeeService.doLogin(data.empId).subscribe(response => {
      if(response != null ){
        //this.employee ==response;
        this.responseMessage="Login Successful !";
        this.router.navigate(['/dashboard']);
      } else {
        this.responseMessage="Invalid Credentials !";
        err => {
          console.log(err);
        }
        //this.responseMessage="Invalid Credentials !";
      }
    });
    }

}