import { CatalogService } from './../service/catalog.service';
import { RentService } from './../service/rent.service';
import { Component, OnInit } from '@angular/core';
import { Rent } from '../model/rent';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../model/book';

@Component({
  selector: 'app-rent-list',
  templateUrl: './rent-list.component.html',
  styleUrls: ['./rent-list.component.css']
})
export class RentListComponent implements OnInit {

  pendingRents: Rent[] | undefined;
  completedRents: Rent[] | undefined;
  book: Book | undefined;

  constructor(
    private rentService: RentService,
    private catalogService: CatalogService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const userID = String(this.route.snapshot.paramMap.get('username')!);
    this.getPendingRents(userID);
    this.getCompletedRents(userID);
  }

  getPendingRents(userID: string) {
    this.rentService.getPendingRents(userID)
      .subscribe(pendRents => {
        this.pendingRents = pendRents;
        this.pendingRents.sort(
          (a, b) => new Date(a.toDate!).getTime() - new Date(b.toDate!).getTime())
      })
  }

  getCompletedRents(userID: string) {
    this.rentService.getCompletedRents(userID)
      .subscribe(compRents => {
        this.completedRents = compRents
        this.completedRents.sort(
          (a, b) => new Date(b.returnDate!).getTime() - new Date(a.returnDate!).getTime());
      })
  }

  returnBook(isbn: string) {
    this.rentService.returnBook(isbn)
      .subscribe(_ => {
        console.log("returned book with isbn: ".concat(isbn));
        this.ngOnInit();
      }
      )
  }

  getBook(isbn: string): Book {
    let book: Book | undefined;
    this.catalogService.getBook(isbn)
      .subscribe(b => book = b)
    return book!;
  }
}
