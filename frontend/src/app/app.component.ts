import { Component } from '@angular/core';

@Component({
  selector: 'st-root',
  template: `
    <st-toolbar></st-toolbar>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
}
