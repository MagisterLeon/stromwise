import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {GoogleSearchResultModel} from '../google-search-result.model';
import {GoogleSearchService} from '../google-search.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'st-google-search-list',
  template: `
    <mat-grid-list cols="1" rowHeight="90px" gutterSize="10px" class="search-result">
      <mat-grid-tile *ngFor="let result of googleSearchResult$ | async">
        <mat-grid-tile-header>
          <mat-icon class="tile-icon" svgIcon="google"></mat-icon>
          <div class="tile-header">
            <div class="url">{{result.url}}</div>
            <a [href]="result.url" target="_blank" class="title">{{result.title}}</a>
          </div>
        </mat-grid-tile-header>
        <div class="description"> {{result.snippet}} </div>
      </mat-grid-tile>
    </mat-grid-list>
  `,
  styleUrls: ['./google-search-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleSearchListComponent implements OnInit {

  @Input() question: string;

  googleSearchResult$: Observable<GoogleSearchResultModel[]>;

  constructor(private googleSearchService: GoogleSearchService) {
  }

  ngOnInit(): void {
    this.googleSearchResult$ = this.googleSearchService.get(this.question);
  }
}
