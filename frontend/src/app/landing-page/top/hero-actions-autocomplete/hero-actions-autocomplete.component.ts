import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {combineLatest, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {TreeNodeNamesService} from './tree-node-names.service';

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
  filteredSkills: Observable<string[]>;

  constructor(private treeNodeNamesService: TreeNodeNamesService) {
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
}
