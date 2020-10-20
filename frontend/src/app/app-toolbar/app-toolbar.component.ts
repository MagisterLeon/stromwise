import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-toolbar',
  template: `
    <mat-toolbar>
      <img class="logo"
           src="assets/logo/StromWise.png"
           alt="image"
           routerLink="/">
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
