import {Component, OnInit} from '@angular/core';
import {CategoryNamesService} from '../hero-actions-autocomplete/category-names.service';
import {Observable} from 'rxjs';
import {Category} from '../hero-actions-autocomplete/category';

@Component({
  selector: 'st-hero-action-buttons',
  template: `
    <div *ngIf="mostPopularCategories$ | async as categories">
      <a *ngFor="let category of categories" [routerLink]=category.path.toLowerCase()>
        <button class="hero-action-button" mat-stroked-button>{{category.name}}</button>
      </a>
    </div>
  `,
  styleUrls: ['./hero-action-buttons.component.scss']
})
export class HeroActionButtonsComponent implements OnInit {

  mostPopularCategories$: Observable<Category[]>;

  constructor(private categoryNamesService: CategoryNamesService) {
  }

  ngOnInit(): void {
    this.mostPopularCategories$ = this.categoryNamesService.getMostPopularCategories();
  }

}
