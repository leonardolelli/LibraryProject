import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Rent } from '../model/rent';

@Injectable({
  providedIn: 'root'
})
export class RentService {

    private url = 'https://localhost:8244/rent/v1/';
    //private url = 'http://localhost:8086/api/rent/';

    httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json' })
    };
    
  constructor(private http: HttpClient) { }

  getStatus(isbn: string): Observable<boolean> {
    return this.http.get<boolean>(this.url.concat("isAvailable?isbn=").concat(isbn))
      .pipe(
        tap(_ => console.log('status has been loaded')),
        catchError(this.handleError<boolean>('getStatus'))
      );
  }

  rentBook(rent: Rent): Observable<Rent> {
    return this.http.post<Rent>(this.url.concat("rentABook"), rent, this.httpOptions).pipe(
      tap((newRent: Rent) => console.log(`added rent for isbn ${newRent.isbn}`)),
      catchError(this.handleError<Rent>('rentBook'))
    );
  }

  getPendingRents(userID: string): Observable<Rent[]> {
    return this.http.get<Rent[]>(this.url.concat("pending_rents/").concat(userID))
    .pipe(
      tap(_ => console.log('pending rents have been loaded')),
      catchError(this.handleError<Rent[]>('getPendingRents'))
    );
  }

  getCompletedRents(userID: string): Observable<Rent[]> {
    return this.http.get<Rent[]>(this.url.concat("completed_rents/").concat(userID))
    .pipe(
      tap(_ => console.log('completed rents have been loaded')),
      catchError(this.handleError<Rent[]>('getCompletedRents'))
    );
  }


  returnBook(isbn: string): Observable<any> {
    return this.http.patch(this.url.concat(isbn), this.httpOptions).pipe(
      tap(_ => console.log(`returned book with isbn: ${isbn}`)),
      catchError(this.handleError<any>('returnBook'))
    );
  }

  getExpectedReturnDate(isbn: string): Observable<Date> {
    return this.http.get<Date>(this.url.concat("expected_return_date/").concat(isbn))
    .pipe(
      tap(_ => console.log('expected return date has been loaded')),
      catchError(this.handleError<Date>('getExpectedReturnDate'))
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
