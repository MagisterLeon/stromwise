import {ChangeDetectionStrategy, Component} from '@angular/core';

@Component({
  selector: 'st-toolbar',
  template: `
    <mat-toolbar>
      <img class="logo"
           src="assets/logo/StromWise.png"
           alt="image"
           routerLink="/">
      <div class="navigation">
        <span class="link" routerLink="/skilltree">Skilltree</span>
        <div class="divider"></div>
        <span class="link">Contact</span>
      </div>
    </mat-toolbar>
  `,
  styleUrls: ['./app-toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppToolbarComponent {

}
