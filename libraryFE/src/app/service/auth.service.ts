import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { OAuthService,  AuthConfig } from 'angular-oauth2-oidc';


@Injectable({ providedIn: 'root' })
export class AuthService {

  authConfig: AuthConfig ;
  sso:any = {
    clientId : 'pdtM_hUvWwV3gbnDecVEWfB1IQUa',
    //user: user1
    //password: Str0ng_Passw0rd!
    serverUrl: 'https://localhost:9443',
    issuer :  '/oauth2/token',
    redirectUri : 'http://localhost:4200/redirect',
    scope: 'openid profile identity email',
    tokenEndpoint:  '/oauth2/token',
    userinfoEndpoint:  '/oauth2/userinfo',
    authorizationEndpoint:  '/oauth2/authorize', 
    jwksEndpoint: '/oauth2/jwks',
    logout: '/oidc/logout',
    revoke: '/oauth2/revoke',
    showDebugInformation: true,
    disableAtHashCheck: true, 
    requireHttps: false,
    responseType: 'code'
  }

  constructor(private oauthService: OAuthService) {
    this.authConfig = {
      issuer: this.sso.serverUrl.concat(this.sso.issuer), 
      redirectUri: this.sso.redirectUri,
      clientId: this.sso.clientId,
      scope: this.sso.scope,
      tokenEndpoint: this.sso.serverUrl.concat(this.sso.tokenEndpoint),
      userinfoEndpoint:  this.sso.serverUrl.concat(this.sso.userinfoEndpoint),
      showDebugInformation: this.sso.showDebugInformation,
      loginUrl:  this.sso.serverUrl.concat(this.sso.authorizationEndpoint),
      logoutUrl: this.sso.serverUrl.concat(this.sso.logout),
      revocationEndpoint: this.sso.serverUrl.concat(this.sso.revoke),
      requireHttps: this.sso.requireHttps,
      disableAtHashCheck: true,
      responseType: this.sso.responseType,
      oidc:true,
      //requestAccessToken:true
      //dummyClientSecret: "Pkcnc8_9zG6NBN4bTrYkRImeCuka"
   }; 
    this.oauthService.setupAutomaticSilentRefresh();
    this.configureOauthService();
  }

  public initFlow() {
    this.oauthService.setupAutomaticSilentRefresh();
    this.oauthService.initLoginFlow();
  }

  public isLogIn(): boolean {
    if (this.oauthService.getAccessToken() === null) {
      return false;
    }
    return true;
  }

  private configureOauthService() {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tryLogin();
  }

  public getAccessToken(): string {
    return this.oauthService.getAccessToken();
  }

  public getIdToken(): string{
    return this.oauthService.getIdToken();
  }

  public getUsername(): string{
    const helper = new JwtHelperService();
    return this.getIdToken()? helper.decodeToken(this.getIdToken())["sub"] : null;
    
  }

  public getFirstName(): string{
    const helper = new JwtHelperService();
    //console.log(this.oauthService.getIdToken());
    return this.getIdToken()? helper.decodeToken(this.getIdToken())["given_name"] : null;
  }

  logout(){
    this.oauthService.logOut();
  }

  revokeTokenAndLogout() {
    this.oauthService.revokeTokenAndLogout();
  }
}