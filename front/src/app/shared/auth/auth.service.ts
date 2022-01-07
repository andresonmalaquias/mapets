import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from 'ngx-webstorage';

import { ApiService } from '../services/api.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(
    private apiService: ApiService,
    private userService: UserService,
    private $localStorage: LocalStorageService,
  ) { }

  getToken() {
    return this.$localStorage.retrieve('token');
  }

  storeAuthenticationToken(jwt: any) {
    this.$localStorage.store('token', jwt);
  }

  getAuthenticationToken() {
    return this.$localStorage.retrieve('token');
  }

  isAuthenticated(): boolean {
    if (this.getToken() && this.userService.getUser()) {
      return true;
    }

    return false;
  }

  authenticate(user, token) {
    this.storeAuthenticationToken(token);
    this.userService.storeAuthenticationUser(user);
  }

  login(credentials: any): Observable<any> {
    return this.apiService
      .post('/auth/', credentials, null, false)
      .pipe(map(authenticateSuccess.bind(this)));

    function authenticateSuccess(this: any, resp: any) {
      if (resp && resp.token && resp.user) {
        this.storeAuthenticationToken(resp.token);
        this.userService.storeAuthenticationUser(resp.user);

        return true;
      }

      return false;
    }
  }

  // logout(): Observable<any> {
  logout() {
    // return this.apiService
    //   .post('/logout', {}, null, true)
    //   .pipe(map(clearStorage.bind(this)));

    // function clearStorage(this: any) {
      this.$localStorage.clear('user');
      this.$localStorage.clear('token');
    // }
  }
}
