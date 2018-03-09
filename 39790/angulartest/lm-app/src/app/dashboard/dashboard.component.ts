import { Component, OnInit } from '@angular/core';
import{Employee} from '../employee';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
model= new Employee();
Id:number; 
value:boolean=true;
  constructor() { 
this.Id=parseInt(localStorage.getItem("mId"));
if(this.Id==0)
  {
  
this.value=false;
  }
  }

  ngOnInit() {
  }

}
