import { ActivatedRoute } from '@angular/router';
import { ReviewService } from './../service/review.service';
import { Component, OnInit } from '@angular/core';
import { Review } from '../model/review';

@Component({
  selector: 'app-user-reviews',
  templateUrl: './user-reviews.component.html',
  styleUrls: ['./user-reviews.component.css']
})
export class UserReviewsComponent implements OnInit {

  reviews: Review[] | undefined;

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const userID = String(this.route.snapshot.paramMap.get('username')!);
    this.getReviewsOfUser(userID);
  }

  getReviewsOfUser(userID: string) {
    this.reviewService.getReviewsOfUser(userID)
      .subscribe(r => {
        this.reviews = r;
        this.reviews.sort(
          (a, b) => new Date(b.lastUpdate!).getTime() - new Date(a.lastUpdate!).getTime());
      }
      );
  }

  delete(id: string) {
    if (confirm("Are you sure?")){
      this.reviewService.delete(id).subscribe(_ => {
        //alert("deleted"); 
        this.ngOnInit()
      });
    }
  }
}



