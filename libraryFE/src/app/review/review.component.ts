
import { Review } from '../model/review';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from '../service/review.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input()
  reviews: Review[] = [];
  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const isbn = String(this.route.snapshot.paramMap.get('isbn')!);
    this.getReviewsForBook(isbn);
  }

  getReviewsForBook(isbn: string) {
    this.reviewService.getReviewsForBook(isbn).subscribe(r => {
      this.reviews = r;
      this.reviews.sort(
        (a, b) => new Date(b.lastUpdate!).getTime() - new Date(a.lastUpdate!).getTime());
    }
    );

  }

}
