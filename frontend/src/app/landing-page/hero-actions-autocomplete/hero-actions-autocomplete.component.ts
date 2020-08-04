import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';

@Component({
  template: `
    <form>
      <mat-form-field class="autocomplete-input">
        <input type="text"
               matInput
               [formControl]="skillsControl"
               [matAutocomplete]="auto">
        <mat-icon class="autocomplete-icon" *ngIf="autocompleteOpened" matSuffix color="primary">arrow_drop_up
        </mat-icon>
        <mat-icon class="autocomplete-icon" *ngIf="!autocompleteOpened" matSuffix color="primary">arrow_drop_down
        </mat-icon>
        <mat-autocomplete autoActiveFirstOption
                          #auto="matAutocomplete"
                          (opened)="onOpened()"
                          (closed)="onClosed()">
          <mat-option *ngFor="let option of filteredSkills | async" [value]="option">
            {{option}}
          </mat-option>
        </mat-autocomplete>
      </mat-form-field>
    </form>
  `,
  selector: 'st-hero-actions-autocomplete',
  styleUrls: ['./hero-actions-autocomplete.component.scss']
})
export class HeroActionsAutocompleteComponent implements OnInit {
  skillsControl = new FormControl();
  autocompleteOpened: boolean;
  skills: string[] = ['Programming', 'Web design', 'Guitar', 'Filmmaking'];
  filteredSkills: Observable<string[]>;

  ngOnInit(): void {
    this.filteredSkills = this.skillsControl.valueChanges.pipe(
      startWith(''),
      map(value => this.filter(value))
    );
  }

  onOpened(): void {
    this.autocompleteOpened = true;
  }

  onClosed(): void {
    this.autocompleteOpened = false;
  }

  private filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.skills.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }
}
