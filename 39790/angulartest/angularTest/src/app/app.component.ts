import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  trainer ='prassana';
  helloWorld() {
    return ("Hello World")
  }
  getRes(){
    throw 'hello';
  }
  typError() {
    throw new TypeError("this is Custom Error");
  }
  stud =["HArish","sri"];
  a1 = {name:"harish"}
  getStatus() : boolean {
    return true;
  }
}
