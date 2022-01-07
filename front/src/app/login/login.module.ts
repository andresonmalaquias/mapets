import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { SharedModule } from '../shared/shared.module';

const loginRoute: Routes = [
  {
    path: '',
    component: LoginComponent,
    // data: {
    //   roles: [],
    // },
    children: [
      {
        path: '',
        component: SignInComponent,
      },
      // {
      //   path: 'forgot-password',
      //   component: ForgotPasswordComponent,
      // },
      // {
      //   path: 'reset-password',
      //   component: ResetPasswordComponent,
      // },
      // {
      //   path: 'first-access',
      //   component: FirstAccessComponent,
      // },
      {
        path: '**',
        redirectTo: '/',
      },
    ],
  }
];

@NgModule({
  declarations: [
    LoginComponent,
    SignInComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
    RouterModule.forChild(loginRoute),
  ]
})
export class LoginModule { }
