import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the TestPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-test',
  templateUrl: 'test.html',
})
export class TestPage {
sum : number;
n1: number;
n2: number;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }
  calc() {
this.sum=((this.n1*1)+(this.n2*1));
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TestPage');
  }

}
