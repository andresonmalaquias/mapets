import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { FullCalendarModule } from '@fullcalendar/angular';

import { MetasComponent } from './metas.component';
import { ListComponent } from './list/list.component';
import { SharedModule } from '../../shared/shared.module';
import { FormComponent } from './form/form.component';

export const metasRoute: Route = {
  path: 'pets',
  component: MetasComponent,
  children: [
    {
      path: '',
      component: ListComponent,
    },
    {
      path: 'novo',
      component: FormComponent,
    },
    {
      path: 'editar/:id',
      component: FormComponent,
    },
  ],
};

@NgModule({
  declarations: [MetasComponent, ListComponent, FormComponent],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    FullCalendarModule,
    ReactiveFormsModule,
  ],
})
export class MetasModule {}
