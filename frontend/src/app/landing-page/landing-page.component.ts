import {Component, OnInit} from '@angular/core';
import {ToolbarApiService} from '../app-toolbar/toolbar-api.service';

@Component({
  selector: 'st-landing-page.component.ts',
  template: `
    <div class="landing-page">
      <img class="logo"
           src="assets/logo/StromWise.png"
           alt="image">
      <div class="hero-actions">
        <div class="hero-actions-autocomplete">
          <st-hero-actions-autocomplete></st-hero-actions-autocomplete>
        </div>
        <st-hero-action-buttons></st-hero-action-buttons>
      </div>
    </div>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  constructor(private toolbarApiService: ToolbarApiService) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(false);
  }
}
