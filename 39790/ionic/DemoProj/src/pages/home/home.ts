import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  name: string;
sname:string;
res:string;
  constructor(public navCtrl: NavController) {
this.name="IONIC session"
  }
show() {
  this.res=this.sname;
}
}
