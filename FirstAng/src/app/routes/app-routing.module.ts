import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserComponent } from '../user/user.component';
import { ListsComponent } from '../lists/lists.component';
import { FinishedComponent } from '../lists/maintain-list/finished/finished.component';
import { MaintainListComponent } from '../lists/maintain-list/maintain-list.component';
import { ProcessComponent } from '../lists/maintain-list/process/process.component';
import {AddListComponent} from '../lists/add-list/add-list.component';
import {AuthGuardService} from './auth-guard.service';
import {LoginComponent} from '../login/login.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'user', canActivate: [AuthGuardService], component: UserComponent},
  { path: 'lists', canActivate: [AuthGuardService], component: ListsComponent, children: [
      { path: 'add', component: AddListComponent },
      { path: ':id/process', component: ProcessComponent },
      { path: ':id/finished', component: FinishedComponent },
      { path: ':id', component: MaintainListComponent }
      ]}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
