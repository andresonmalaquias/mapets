import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanLoad,
  Router,
} from '@angular/router';

import { UserService } from './user.service';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserRouteAccessService implements CanLoad, CanActivate {
  constructor(
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const roles = route.data.authorities;
    return this.checkLogin(roles);
  }

  canLoad(): boolean {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }

  checkLogin(roles: any): boolean {
    // if (!roles || roles.length === 0) {
    //   return true;
    // }

    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    } // else if (!this.userService.hasAnyRole(roles)) {
    //   this.router.navigate(['/']);
    //   return false;
    // }

    return true;
  }
}
