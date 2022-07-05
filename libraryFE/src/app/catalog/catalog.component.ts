import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Book } from '../model/book';
import { CatalogService } from '../service/catalog.service';


@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  catalog: Book[] = [];

  searchForm = new FormControl('');

  
  constructor(private catalogService: CatalogService) {
  }

  ngOnInit(): void {
    this.getCatalog();
    
  }

  getCatalog(): void {
    this.catalogService.getCatalog().subscribe(catalog => this.catalog = catalog);
  }

  search(){
    let filter = this.searchForm.value;
    if(filter){
    this.catalogService.getFilteredCatalog(filter)
    .subscribe(filteredCatalog => this.catalog = filteredCatalog);
    }
    else {
      window.alert("You didn't type anything")
    }
  }

}
