import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule,Route} from '@angular/router';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ResetComponent } from './reset/reset.component';
import { MenuComponent } from './menu/menu.component';
import { TableComponent } from './table/table.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    ResetComponent,
    MenuComponent,
    TableComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
{path:'/Login',component:LoginComponent},
{path:'/SignUp',component:SignUpComponent},
{path:'/Reset',component:ResetComponent},
{path:'./Path',component:TableComponent},
{path:'/Menu',component:MenuComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
