import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, of } from 'rxjs';
import { Review } from '../model/review';


@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private url = 'https://localhost:8244/review/v1/';
  //private url = 'http://localhost:8086/api/review/';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json' })
  };

  getReviewsForBook(isbn: string): Observable<Review[]> {
    return this.http.get<Review[]>(this.url.concat("book/").concat(isbn))
      .pipe(
        tap(_ => console.log('reviews have been loaded')),
        catchError(this.handleError<Review[]>('getsForBook'))
      );
  }

  getReviewsOfUser(userID: string): Observable<Review[]> {
    return this.http.get<Review[]>(this.url.concat("user/"+userID))
      .pipe(
        tap(_ => console.log('reviews have been loaded')),
        catchError(this.handleError<Review[]>('getReviewsOfUser'))
      );
  }

  postAReview(review: Review): Observable<Review> {
    return this.http.post<Review>(this.url.concat("post"), review, this.httpOptions).pipe(
      tap((newReview: Review) => console.log(`added review for isbn ${newReview.isbn}`)),
      catchError(this.handleError<Review>('postAReview'))
    );
  }

  delete(id: string): Observable<any> {
    return this.http.delete<any>(this.url.concat("delete/"+id))
      .pipe(
        tap(_ => console.log(`deleted review with id ${id}`)),
        catchError(this.handleError<any>('delete'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      window.alert(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
