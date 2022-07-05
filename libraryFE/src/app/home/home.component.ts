import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLogIn();
    if (this.isLoggedIn) {
      this.username = this.authService.getUsername();
    }
  }

  username: any;
  isLoggedIn = false;

}
