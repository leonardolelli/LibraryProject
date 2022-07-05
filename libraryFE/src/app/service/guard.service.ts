import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

  @Injectable({
    providedIn: 'root'
  })
export class GuardService implements CanActivate {
  constructor(private router: Router, private oauthService: OAuthService) {}

  canActivate() {
    if (
      this.oauthService.hasValidAccessToken() &&
      this.oauthService.hasValidIdToken()
    ) {
      return true;
    } else {
      window.alert("You need to login to use this functionality")
      this.router.navigate(['/login']);
      return false;
    }
  }
}