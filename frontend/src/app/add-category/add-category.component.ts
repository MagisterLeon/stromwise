import {Component, OnInit} from '@angular/core';
import {AddCategoryService} from './add-category.service';
import {Router} from '@angular/router';
import {ToolbarApiService} from "../app-toolbar/toolbar-api.service";

@Component({
  selector: 'st-add-category',
  template: `
    <h1>Add category</h1>
    <form class="add-category-form" [formGroup]="addCategoryService.form"
          (ngSubmit)="addCategoryService.onSubmit()">
      <mat-form-field appearance="outline">
        <mat-label>category name</mat-label>
        <input
          matInput
          formControlName="categoryName"
          required>
        <mat-error *ngIf="addCategoryService.form.get('categoryName').hasError('required')">Category name is required
        </mat-error>
      </mat-form-field>
      <mat-form-field class="text-area" appearance="outline">
        <mat-label>question</mat-label>
        <textarea
          matInput
          formControlName="question"
          required></textarea>
        <mat-error *ngIf="addCategoryService.form.get('question').hasError('required')">Question is required</mat-error>
      </mat-form-field>
      <mat-form-field class="text-area" appearance="outline">
        <mat-label>answer</mat-label>
        <textarea
          matInput
          formControlName="answer"></textarea>
      </mat-form-field>
      <div>
        <button class="cancel-button" type="button"
                mat-stroked-button
                (click)="onCancel()"
        >Cancel
        </button>
        <button class="confirm-button" type="submit"
                mat-flat-button
                [disabled]="!addCategoryService.form.valid">Send
        </button>
      </div>
    </form>
  `,
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {

  constructor(public addCategoryService: AddCategoryService,
              private toolbarApiService: ToolbarApiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(true);
  }

  onCancel(): void {
    this.router.navigateByUrl('');
  }
}
