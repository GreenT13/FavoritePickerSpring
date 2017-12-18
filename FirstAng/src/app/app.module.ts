import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {CommonModule} from '@angular/common';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './routes/app-routing.module';
import { UserComponent } from './user/user.component';
import { ListsComponent } from './lists/lists.component';
import { MaintainListComponent } from './lists/maintain-list/maintain-list.component';
import { ProcessComponent } from './lists/maintain-list/process/process.component';
import { FinishedComponent } from './lists/maintain-list/finished/finished.component';
import { ListService } from './services/listservice.service';
import { AlgorithmService} from './services/algorithm.service';
import { AddListComponent } from './lists/add-list/add-list.component';
import { HttpClient } from './services/httpclient.service';
import { LoginComponent } from './login/login.component';
import {AuthGuardService} from './routes/auth-guard.service';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ListsComponent,
    MaintainListComponent,
    ProcessComponent,
    FinishedComponent,
    AddListComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CommonModule,
    AppRoutingModule
  ],
  providers: [ListService, AlgorithmService, HttpClient, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
