import {ChangeDetectionStrategy, Component} from '@angular/core';

@Component({
  selector: 'st-google-search',
  template: `
    <st-google-search-list></st-google-search-list>
  `,
  styleUrls: ['./google-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleSearchComponent {
}
