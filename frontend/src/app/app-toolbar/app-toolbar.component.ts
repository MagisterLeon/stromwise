import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ToolbarApiService} from './toolbar-api.service';

@Component({
  selector: 'st-toolbar',
  template: `
    <mat-toolbar *ngIf="toolbarApiService.isVisible() | async">
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

  constructor(public toolbarApiService: ToolbarApiService) {
  }

  ngOnInit(): void {
  }
}
