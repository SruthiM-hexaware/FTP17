import {Routes} from '@angular/router';
import { ApplyleaveComponent} from './applyleave/applyleave.component';
import { ListpendingComponent } from './listpending/listpending.component';
import { ApprovedenyComponent } from './approvedeny/approvedeny.component';

export const rootRouterConfig: Routes = [
    { path:'', redirectTo:'showpending',pathMatch:'full'},
    { path:'showpending', component:ListpendingComponent},     
    { path:'apply', component:ApplyleaveComponent},
    { path:'approvedeny', component:ApprovedenyComponent}     
];