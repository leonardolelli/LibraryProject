import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  idToken: any;
  username: any;
  isLoggedIn: any;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLogIn();
  }

  getUsername(){
   return this.authService.getUsername();
  }

  userRents(){
    this.router.navigate(['rent_list/'.concat(this.getUsername())]);
  }

  userReviews(){
    this.router.navigate(['review_list/'.concat(this.getUsername())]);
  }


  logout(){
    this.authService.revokeTokenAndLogout();
  }

}


