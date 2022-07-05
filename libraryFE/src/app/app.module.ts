import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OAuthModule } from 'angular-oauth2-oidc';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RedirectComponent } from './redirect/redirect.component';
import { HomeComponent } from './home/home.component';
import { NavComponent } from './nav/nav.component';
import { CatalogComponent } from './catalog/catalog.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { ReviewComponent } from './review/review.component';
import { RentComponent } from './rent/rent.component';
import { RentListComponent } from './rent-list/rent-list.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UserReviewsComponent } from './user-reviews/user-reviews.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RedirectComponent,
    HomeComponent,
    NavComponent,
    CatalogComponent,
    BookDetailComponent,
    ReviewComponent,
    RentComponent,
    RentListComponent,
    AddReviewComponent,
    UserReviewsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    OAuthModule.forRoot({
      resourceServer: {
          allowedUrls: ['https://localhost:8244'],
          sendAccessToken: true
      }
  })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }