import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {combineLatest, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {CategoryNamesService} from './category-names.service';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {Router} from '@angular/router';
import {Category} from './category';

@Component({
  template: `
    <form>
      <mat-form-field class="autocomplete-input">
        <input type="text"
               matInput
               #autocompleteInput
               [formControl]="categoryControl"
               [matAutocomplete]="auto">
        <mat-icon class="autocomplete-icon" *ngIf="autocompleteOpened" matSuffix color="primary">arrow_drop_up
        </mat-icon>
        <mat-icon class="autocomplete-icon" *ngIf="!autocompleteOpened" matSuffix color="primary">arrow_drop_down
        </mat-icon>
        <mat-autocomplete autoActiveFirstOption
                          #auto="matAutocomplete"
                          (optionSelected)="onCategorySelected($event, autocompleteInput)"
                          (opened)="onOpened()"
                          (closed)="onClosed()">
          <mat-option *ngFor="let option of filteredCategories | async" [value]="option.path">
            {{option.name}}
          </mat-option>
        </mat-autocomplete>
      </mat-form-field>
    </form>
  `,
  selector: 'st-hero-actions-autocomplete',
  styleUrls: ['./hero-actions-autocomplete.component.scss']
})
export class HeroActionsAutocompleteComponent implements OnInit {
  categoryControl = new FormControl();
  autocompleteOpened: boolean;
  filteredCategories: Observable<Category[]>;

  constructor(private router: Router,
              private categoryNamesService: CategoryNamesService) {
  }

  ngOnInit(): void {
    this.filteredCategories = combineLatest([
      this.categoryNamesService.getCategories(),
      this.categoryControl.valueChanges.pipe(
        startWith('')
      )])
      .pipe(
        map(([categories, value]) => this.filter(categories, value))
      );
  }

  onOpened(): void {
    this.autocompleteOpened = true;
  }

  onClosed(): void {
    this.autocompleteOpened = false;
  }

  private filter(categories: Category[], value: string): Category[] {
    const filterValue = value.toLowerCase();
    const filteredCategories = categories.filter(option => option.name.toLowerCase().indexOf(filterValue) === 0);
    filteredCategories.push({name: 'Not found your category? Add it!', path: 'add/category'});
    return filteredCategories;
  }

  onCategorySelected(event: MatAutocompleteSelectedEvent, autocompleteInput: HTMLInputElement): void {
    autocompleteInput.value = '';
    autocompleteInput.blur();
    this.router.navigateByUrl(event.option.value.toLowerCase());
  }
}
