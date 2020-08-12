import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {combineLatest, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {TreeNodeNamesService} from './tree-node-names.service';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {Router} from '@angular/router';

@Component({
  template: `
    <form>
      <mat-form-field class="autocomplete-input">
        <input type="text"
               matInput
               #autocompleteInput
               [formControl]="skillsControl"
               [matAutocomplete]="auto">
        <mat-icon class="autocomplete-icon" *ngIf="autocompleteOpened" matSuffix color="primary">arrow_drop_up
        </mat-icon>
        <mat-icon class="autocomplete-icon" *ngIf="!autocompleteOpened" matSuffix color="primary">arrow_drop_down
        </mat-icon>
        <mat-autocomplete autoActiveFirstOption
                          #auto="matAutocomplete"
                          (optionSelected)="onSkillSelected($event, autocompleteInput)"
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
  filteredSkills: Observable<string[]>;

  constructor(private router: Router,
              private treeNodeNamesService: TreeNodeNamesService) {
  }

  ngOnInit(): void {
    this.filteredSkills = combineLatest([
      this.treeNodeNamesService.nodeNames(),
      this.skillsControl.valueChanges.pipe(
        startWith('')
      )])
      .pipe(
        map(([names, value]) => this.filter(names, value))
      );
  }

  onOpened(): void {
    this.autocompleteOpened = true;
  }

  onClosed(): void {
    this.autocompleteOpened = false;
  }

  private filter(names: string[], value: string): string[] {
    const filterValue = value.toLowerCase();
    return names.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }

  onSkillSelected(event: MatAutocompleteSelectedEvent, autocompleteInput: HTMLInputElement): void {
    autocompleteInput.value = '';
    autocompleteInput.blur();
    this.router.navigate(['skilltree', event.option.value])
  }
}
