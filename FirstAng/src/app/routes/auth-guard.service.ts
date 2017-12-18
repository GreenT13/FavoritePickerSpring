import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '../services/httpclient.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.httpClient.isLoggedIn()) {
        this.router.navigate(['/login']);
    } else {
      return true;
    }
  }

  constructor(private httpClient: HttpClient, private router: Router) { }

}
