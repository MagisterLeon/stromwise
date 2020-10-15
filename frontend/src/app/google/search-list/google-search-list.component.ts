import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {GoogleSearchResultModel} from '../google-search-result.model';

@Component({
  selector: 'st-google-search-list',
  template: `
    <mat-list class="search-result">
      <mat-list-item *ngFor="let result of googleSearchResult">
        <div mat-line class="url">{{result.url}}</div>
        <a [href]="result.url" target="_blank" mat-line class="title">{{result.title}}</a>
        <div mat-line class="description"> {{result.snippet}} </div>
      </mat-list-item>
    </mat-list>
  `,
  styleUrls: ['./google-search-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleSearchListComponent implements OnInit {

  googleSearchResult: GoogleSearchResultModel[] = [];

  constructor() {
  }

  ngOnInit(): void {
  }
}
