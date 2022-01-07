import { Injectable, Injector } from '@angular/core';
import {
  HttpEvent,
  HttpErrorResponse,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { AuthService } from '../auth/auth.service';
import { LoadService } from '../services/load.service';

@Injectable({
  providedIn: 'root',
})
export class AuthExpiredInterceptor implements HttpInterceptor {
  constructor(private injector: Injector) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {
        const router: Router = this.injector.get(Router);

        // you can redirect for a specific unauthorized page
        if (router.url && !router.url.startsWith('/login') && err.status === 401) {
          const authService: AuthService = this.injector.get(AuthService);

          // authService.logout().subscribe(
          //   (data) => {
          //     this.expiredSession();
          //   },
          //   (error) => {
          //     this.expiredSession();
          //   }
          // );
        }

        return throwError(err);
      })
    );
  }

  expiredSession() {
    const router: Router = this.injector.get(Router);
    const loadService: LoadService = this.injector.get(LoadService);

    router.navigate(['login']);
    setTimeout(() => {
      console.log("Erro ao acessar rota")
      // loadService.emitMessageEvent('error.session');
    }, 1000);
  }
}
