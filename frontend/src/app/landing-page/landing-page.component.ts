import {Component, OnInit} from '@angular/core';
import {ToolbarSize} from '../app-toolbar/toolbar-size.enum';
import {ToolbarApiService} from '../app-toolbar/toolbar-api.service';

@Component({
  selector: 'st-landing-page.component.ts',
  template: `
    <st-landing-page-top></st-landing-page-top>
    <st-about-us></st-about-us>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  constructor(private toolbarApi: ToolbarApiService) {
  }

  ngOnInit(): void {
    this.toolbarApi.setToolbarSize(ToolbarSize.BIG);
  }
}
