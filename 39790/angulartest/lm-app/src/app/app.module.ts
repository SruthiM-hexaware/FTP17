import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomepageComponent } from './homepage/homepage.component';
import {EmployeeService} from './employee.service';
import {LeaveService} from './leave.service';
import { LeavehistoryComponent } from './leavehistory/leavehistory.component';
import { MyDetailsComponent } from './my-details/my-details.component';
import { MyManagerComponent } from './my-manager/my-manager.component';
import { PendingLeavesComponent } from './pending-leaves/pending-leaves.component';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';
import { ApplyleaveComponent } from './applyleave/applyleave.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    HomepageComponent,
    LeavehistoryComponent,
    MyDetailsComponent,
    MyManagerComponent,
    PendingLeavesComponent,
    ApproveDenyComponent,
    ApplyleaveComponent
  ],
  imports: [
    BrowserModule,FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path:'',component:HomepageComponent},
      {path:'login', component:LoginComponent},
      {path:'dashboard',component:DashboardComponent},
      {path:'leavehis',component:LeavehistoryComponent},
      {path:'Mydetails',component:MyDetailsComponent},
      {path:'Mymanager',component:MyManagerComponent},
      {path:'PendingLeaves',component:PendingLeavesComponent},
      {path:'approveDeny',component:ApproveDenyComponent},
      {path:'applyleave',component:ApplyleaveComponent}
      
    ])
  ],
  providers: [EmployeeService, LeaveService],
  bootstrap: [AppComponent]
})
export class AppModule { }