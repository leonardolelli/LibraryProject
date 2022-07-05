import { AuthService } from './../service/auth.service';
import { CatalogService } from './../service/catalog.service';
import { ReviewService } from './../service/review.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../model/book';
import { Review } from '../model/review';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  book: Book | undefined;
  isbn : string | undefined;
  username: string | undefined;
  review: Review | undefined;
  scores = new Array(1,2,3,4,5);

  constructor(
    private reviewService: ReviewService,
    private catalogService: CatalogService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    ) { }

    reviewForm = new FormGroup({
      score: new FormControl(''),
      text: new FormControl(''),
    });

  ngOnInit(): void {
    this.isbn = String(this.route.snapshot.paramMap.get('isbn')!);
    this.getBook(this.isbn);
    this.username = this.authService.getUsername();
  }

  getBook(isbn: string) {
    this.catalogService.getBook(isbn).subscribe(b => this.book = b);
  }

  onSubmit(): void {
    let scoreInput = parseInt(this.reviewForm.get('score')?.value!);
    let textInput = this.reviewForm.get('text')?.value!;
    this.review = {
      isbn: this.isbn!,
      username: this.username!,
      score: scoreInput,
      text: textInput
    }

    this.reviewService.postAReview(this.review)
    .subscribe(r => 
      {
        window.alert("Aggiunta recensione per isbn: ".concat(r.isbn));
        this.router.navigate(['detail/'.concat(r.isbn)]);
      })
  }

}
