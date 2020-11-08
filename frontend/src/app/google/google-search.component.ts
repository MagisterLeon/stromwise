import {ChangeDetectionStrategy, Component, Input} from '@angular/core';

@Component({
  selector: 'st-google-search',
  template: `
    <mat-tab-group mat-stretch-tabs class="google-search-tabs" mat-align-tabs="center"
                   color="accent" animationDuration="0ms">
      <mat-tab label="All"></mat-tab>
      <mat-tab label="Recommended"></mat-tab>
      <mat-tab label="Web"></mat-tab>
    </mat-tab-group>

    <st-google-search-list [question]="question"></st-google-search-list>
  `,
  styleUrls: ['./google-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GoogleSearchComponent {

  @Input() question: string;
}
