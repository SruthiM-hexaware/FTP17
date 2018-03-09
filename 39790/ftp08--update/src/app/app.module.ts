import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { Routes } from '@angular/router';
import { rootRouterConfig } from './app.routes';
import { RouterModule } from '@angular/router';
import { FormsModule,  ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MyleaveComponent } from './myleave/myleave.component';
import {EmployeeService} from "./employee.service";
import { HomeComponent } from './home/home.component';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';
import { ManagerDetailsComponent } from './manager-details/manager-details.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MyDetailsComponent } from './my-details/my-details.component';
import { ListpendingComponent } from './listpending/listpending.component';
import { ApplyleaveComponent} from './applyleave/applyleave.component';



@NgModule({
  declarations: [
    AppComponent,
    ManagerDetailsComponent,
    ApproveDenyComponent,
    LoginComponent,
    MyleaveComponent,
    HomeComponent,
    DashboardComponent,
    MyDetailsComponent,
    ListpendingComponent,
    ApplyleaveComponent
  ],
  imports: [
    BrowserModule,RouterModule.forRoot(rootRouterConfig),
    HttpModule,FormsModule,ReactiveFormsModule
    
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
