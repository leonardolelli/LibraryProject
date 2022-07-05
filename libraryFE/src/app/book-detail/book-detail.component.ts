
import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { ActivatedRoute, Router } from '@angular/router';
import { CatalogService } from '../service/catalog.service';
import { RentService } from '../service/rent.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  book: Book | undefined;
  status: boolean | undefined;
  amIinRent = this.router.url.includes("/renting/");
  constructor (
    private catalogService: CatalogService,
    private rentService: RentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    const isbn = String(this.route.snapshot.paramMap.get('isbn')!);
    this.getStatus(isbn);
    this.getBook(isbn);
  }

  getBook(isbn: string) {
    this.catalogService.getBook(isbn).subscribe(b => this.book = b);
  }

  getStatus(isbn: string){
    this.rentService.getStatus(isbn).subscribe(s => this.status = s);
  }
}
