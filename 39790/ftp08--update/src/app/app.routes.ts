import { ManagerDetailsComponent} from './manager-details/manager-details.component';
import { ApproveDenyComponent } from './approve-deny/approve-deny.component';
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MyleaveComponent } from './myleave/myleave.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MyDetailsComponent } from './my-details/my-details.component';
import { ListpendingComponent } from './listpending/listpending.component';
import { ApplyleaveComponent} from './applyleave/applyleave.component';

export const rootRouterConfig : Routes = [
    { path : '', redirectTo : 'home', pathMatch: 'full'},
    { path : 'login', component : LoginComponent},
    { path : 'myleave/:empId', component : MyleaveComponent},
    { path : 'home', component : HomeComponent},
    { path : 'my-details/:empId', component : MyDetailsComponent},
    { path: 'approvedeny', component: ApproveDenyComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path:'showpending', component:ListpendingComponent},
    { path:'manager-details', component:ManagerDetailsComponent},
    { path:'apply', component:ApplyleaveComponent},
];
