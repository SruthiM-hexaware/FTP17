import { Component, OnInit } from '@angular/core';
import{login} from '../login';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model = new login();
  constructor(private _router : Router) { 
    this.model.username=localStorage.getItem("empId");
  }
  show() {
    if(this.model.password==null) {
      alert("Please enter your password...");
    } else {
      this._router.navigate(['/dashboard']);
    }
    
  }

  ngOnInit() {
  }

}
