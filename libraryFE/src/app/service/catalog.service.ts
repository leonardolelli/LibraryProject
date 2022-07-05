import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Book } from '../model/book';


@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  private url = 'https://localhost:8244/catalog/v1/';
  //private url = 'http://localhost:8086/api/catalog/';

  constructor(private http: HttpClient) { 
  }

  getCatalog(): Observable<Book[]> {
    return this.http.get<Book[]>(this.url.concat("index"))
      .pipe(
        tap(_ => console.log('catalog has been loaded')),
        catchError(this.handleError<Book[]>('getCatalog', []))
      );
  }

  getBook(isbn: string): Observable<Book> {
    return this.http.get<Book>(this.url.concat(isbn))
    .pipe(
      tap(_ => console.log(`Book ${isbn} has been loaded`)),
      catchError(this.handleError<Book>('getBook'))
    );
  }

  getFilteredCatalog(filter: string): Observable<Book[]> {
    return this.http.get<Book[]>(this.url.concat("findAllBy?genre="+filter))
      .pipe(
        tap(_ => console.log('filtered catalog has been loaded')),
        catchError(this.handleError<Book[]>('getFilteredCatalog', []))
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
