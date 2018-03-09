import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ApplyleaveComponent } from './applyleave/applyleave.component';
import {rootRouterConfig} from './app.route';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from './employee.service';
import { ListpendingComponent } from './listpending/listpending.component';
import { ApprovedenyComponent } from './approvedeny/approvedeny.component';

@NgModule({
  declarations: [
    AppComponent, ApplyleaveComponent, ListpendingComponent, ApprovedenyComponent, 
  ],
  imports: [
    BrowserModule,
    HttpModule, RouterModule.forRoot(rootRouterConfig), FormsModule
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
