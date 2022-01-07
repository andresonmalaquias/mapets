import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../shared/shared.module';
import { HomeComponent } from './home.component';
import { metasRoute } from './metas/metas.module';
import { DashboardComponent } from './dashboard/dashboard.component';

export const homeRoute: Route = {
  path: '',
  component: HomeComponent,
  // canActivate: [UserRouteAccessService],
  // data: {
  //   roles: [],
  // },
  children: [
    metasRoute,
    // {
    //   path: '',
    //   component: DashboardComponent,
    // },
    {
      path: '',
      redirectTo: '/pets',
    },
  ],
};

@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild([homeRoute]),
  ],
})
export class HomeModule {}
