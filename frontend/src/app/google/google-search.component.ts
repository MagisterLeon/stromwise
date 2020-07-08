import {Component, OnInit} from '@angular/core';
import {GoogleSearchService} from './google-search.service';
import {Observable} from 'rxjs';
import {GoogleSearchResultModel} from './google-search-result.model';

@Component({
  selector: 'st-google-search',
  template: `
    <st-google-search-list [googleSearchResult]="googleSearchResult$ | async"></st-google-search-list>
  `,
  styleUrls: ['./google-search.component.scss']
})
export class GoogleSearchComponent implements OnInit {

  googleSearchResult$: Observable<GoogleSearchResultModel[]>;

  constructor(private googleSearchService: GoogleSearchService) {
  }

  ngOnInit(): void {
    this.googleSearchResult$ = this.googleSearchService.get('dupa');
  }
}
