import {Component, OnInit} from '@angular/core';
import {ToolbarApiService} from "./app-toolbar/toolbar-api.service";

@Component({
  selector: 'st-root',
  template: `
    <st-toolbar *ngIf="toolbarApiService.isVisible()"></st-toolbar>
    <router-outlet></router-outlet>
    <st-footer></st-footer>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(public toolbarApiService: ToolbarApiService) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(false);
  }
}
