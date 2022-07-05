import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReviewComponent } from './add-review/add-review.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { CatalogComponent } from './catalog/catalog.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RedirectComponent } from './redirect/redirect.component';
import { RentListComponent } from './rent-list/rent-list.component';
import { RentComponent } from './rent/rent.component';
import { GuardService } from './service/guard.service';
import { UserReviewsComponent } from './user-reviews/user-reviews.component';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full"},
  { path: 'login', component: LoginComponent },
  { path: 'redirect', component: RedirectComponent },
  { path: 'home', component: HomeComponent },
  { path: 'catalog', component:CatalogComponent },
  { path: 'detail/:isbn', component:BookDetailComponent },
  { path: 'renting/:isbn', component:RentComponent, canActivate : [GuardService]},
  { path: 'rent_list/:username', component:RentListComponent, canActivate : [GuardService] },
  { path: 'add_review/:isbn', component:AddReviewComponent, canActivate : [GuardService] },
  { path: 'review_list/:username', component:UserReviewsComponent, canActivate : [GuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
