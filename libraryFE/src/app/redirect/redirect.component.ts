import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-redirect',
  templateUrl: './redirect.component.html',
  styleUrls: ['./redirect.component.css']
})
export class RedirectComponent implements OnInit {

  constructor(private router: Router) { }
  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigate(['']);
    }, 1000);
  }
}
