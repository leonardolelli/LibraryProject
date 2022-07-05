import { AuthService } from './../service/auth.service';
import { RentService } from './../service/rent.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Rent } from '../model/rent';
import { FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})
export class RentComponent implements OnInit {

  selectedDate: Date | undefined;
  rent: Rent | undefined;
  isAvailable=true;
  returnDate: Date | undefined;
  today = new Date().toISOString().slice(0,10);

  toDateForm = new FormControl(this.today,[ 
    Validators.required
  ]);
  
  constructor(
    private route: ActivatedRoute, 
    private rentService: RentService,
    private authService: AuthService,
    private router: Router) {
    }

  ngOnInit(): void {
    const isbn = String(this.route.snapshot.paramMap.get('isbn')!);
    this.getStatus(isbn);
    this.getExpectedReturnDate(isbn);
    
  }

  getDate(event: any) {
    this.selectedDate = event.target.value;
  }

  rentBook(): void{
    const isbn = String(this.route.snapshot.paramMap.get('isbn')!);
    const userID = this.authService.getUsername();
    console.log(userID);
    this.rent = {
      isbn: isbn,
      toDate:this.selectedDate!,
      user : {
        username: userID
      }
    }

    this.rentService.rentBook(this.rent)
    .subscribe(r => 
      {
        window.alert("Aggiunta prenotazione per isbn: ".concat(r.isbn));
        this.router.navigate(['rent_list/'.concat(userID)]);
      })
      
  }

  getStatus(isbn:string){
    this.rentService.getStatus(isbn).subscribe(s => this.isAvailable=s)
  }

  getExpectedReturnDate(isbn:string){
    this.rentService.getExpectedReturnDate(isbn).subscribe(rd => this.returnDate=rd)
  }

}
