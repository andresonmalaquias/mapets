import { Injectable } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';

import { ApiService } from '../services/api.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private $localStorage: LocalStorageService,
    private apiService: ApiService
  ) { }

  getUser() {
    return this.$localStorage.retrieve('user');
  }

  observeUser() {
    return this.$localStorage.observe('user');
  }

  storeAuthenticationUser(user: any) {
    this.$localStorage.store('user', user);
  }

  // hasAnyRole(roles: string[]): boolean {
  //   const user = this.getUser();

  //   for (const role of roles) {
  //     if (user && user.role === role) {
  //       return true;
  //     }
  //   }

  //   return false;
  // }

  // hasRole(role: string): boolean {
  //   return this.hasAnyRole([role]);
  // }

  // isAdmin() {
  //   return this.hasRole(Role.ADMIN);
  // }

  updateUser() {
    if (this.getUser()) {
      this.apiService
        .get('/users/me')
        .subscribe((user) => this.storeAuthenticationUser(user));
    }
  }

  updatePassword(passwords) {
    const body = {
      newPassword: passwords.newPassword,
      currentPassword: passwords.currentPassword,
    };

    return this.apiService.post('/users/password', body);
  }
}
