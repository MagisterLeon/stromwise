import { Component } from '@angular/core';

@Component({
  selector: 'st-root',
  template: `
    <router-outlet></router-outlet>
    <st-footer></st-footer>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
}
