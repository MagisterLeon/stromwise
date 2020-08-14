import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {GoogleSearchResultModel} from '../google-search-result.model';
import {SkillTreeState} from '../../skill-tree/skill-tree.state';
import {GoogleSearchService} from '../google-search.service';
import {switchMap} from 'rxjs/operators';

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
