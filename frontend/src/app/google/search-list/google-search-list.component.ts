import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {GoogleSearchResultModel} from '../google-search-result.model';
import {SkillTreeState} from '../../skill-tree/skill-tree.state';
import {GoogleSearchService} from '../google-search.service';
import {switchMap} from 'rxjs/operators';

@Component({
  selector: 'st-google-search-list',
  template: `
    <mat-grid-list cols="2" rowHeight="90px" gutterSize="10px" class="search-result">
      <mat-grid-tile *ngFor="let result of googleSearchResult">
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

  googleSearchResult: GoogleSearchResultModel[] = [];

  constructor(private ref: ChangeDetectorRef,
              private googleSearchService: GoogleSearchService,
              private skillTreeState: SkillTreeState) {
  }

  ngOnInit(): void {
    this.skillTreeState.nodeSelected().pipe(
      switchMap(o => this.googleSearchService.get(o.name))
    ).subscribe(searchResult => {
      this.googleSearchResult = searchResult;
      this.ref.detectChanges();
    });
  }
}
