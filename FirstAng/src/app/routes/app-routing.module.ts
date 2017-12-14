import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserComponent } from '../user/user.component';
import { ListsComponent } from '../lists/lists.component';
import { FinishedComponent } from '../lists/maintain-list/finished/finished.component';
import { MaintainListComponent } from '../lists/maintain-list/maintain-list.component';
import { ProcessComponent } from '../lists/maintain-list/process/process.component';
import {AddListComponent} from '../lists/add-list/add-list.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'user', component: UserComponent},
  { path: 'lists/add', component: AddListComponent },
  { path: 'lists/:id/process', component: ProcessComponent },
  { path: 'lists/:id/finished', component: FinishedComponent },
  { path: 'lists/:id', component: MaintainListComponent },
  { path: 'lists', component: ListsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
