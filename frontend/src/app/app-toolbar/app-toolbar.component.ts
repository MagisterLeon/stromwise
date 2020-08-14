import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ToolbarApiService} from './toolbar-api.service';
import {ToolbarSize} from './toolbar-size.enum';
import {combineLatest, Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {FormControl} from '@angular/forms';
import {TreeNodeNamesService} from '../landing-page/top/hero-actions-autocomplete/tree-node-names.service';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {Router} from '@angular/router';

@Component({
  selector: 'st-toolbar',
  template: `
    <mat-toolbar *ngIf="toolbarApi.getToolbarSize() | async as size"
                 [ngClass]="size">
      <img class="logo"
           [ngClass]="size"
           src="assets/logo/StromWise.png"
           alt="image"
           routerLink="/">
      <mat-form-field *ngIf="isCompact(size)" floatLabel="never">
        <input type="text"
               matInput
               #autocompleteInput
               [formControl]="skillsControl"
               [matAutocomplete]="auto">
        <mat-placeholder class="search-placeholder">Search...</mat-placeholder>
        <mat-icon matSuffix class="search-icon">search</mat-icon>
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
      <div class="navigation">
        <span class="link" routerLink="/skilltree">Skilltree</span>
        <div class="divider"></div>
        <span class="link">Contact</span>
      </div>
    </mat-toolbar>
  `,
  styleUrls: ['./app-toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppToolbarComponent implements OnInit {

  skillsControl = new FormControl();
  autocompleteOpened: boolean;
  filteredSkills: Observable<string[]>;

  constructor(private treeNodeNamesService: TreeNodeNamesService,
              private router: Router,
              public toolbarApi: ToolbarApiService) {
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

  isCompact(size: ToolbarSize): boolean {
    return size === 'compact';
  }


  onSkillSelected(event: MatAutocompleteSelectedEvent, autocompleteInput: HTMLInputElement): void {
    autocompleteInput.value = '';
    autocompleteInput.blur();
    this.router.navigate(['skilltree', event.option.value]);
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
