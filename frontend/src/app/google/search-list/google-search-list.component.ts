import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {GoogleSearchResultModel} from '../google-search-result.model';

@Component({
  selector: 'st-google-search-list',
  template: `
    <mat-list class="search-result">
      <div mat-subheader>searchQuery</div>
      <mat-list-item *ngFor="let result of googleSearchResult">
        <div mat-line>{{result.title}}</div>
        <div mat-line> {{result.snippet}} </div>
      </mat-list-item>
    </mat-list>
  `,
  styleUrls: ['./google-search-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleSearchListComponent {

  @Input() googleSearchResult: GoogleSearchResultModel[];
}
