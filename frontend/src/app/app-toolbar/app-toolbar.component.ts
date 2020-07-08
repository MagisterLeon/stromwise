import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-toolbar',
  template: `
    <mat-toolbar color="accent">
      <span>StromWise</span>
    </mat-toolbar>
  `,
  styleUrls: ['./app-toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppToolbarComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
