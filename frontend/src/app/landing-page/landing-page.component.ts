import {Component, OnInit} from '@angular/core';
import {ToolbarApiService} from '../app-toolbar/toolbar-api.service';
import {Router} from "@angular/router";

@Component({
  selector: 'st-landing-page.component.ts',
  template: `
    <div class="landing-page">
      <img class="logo"
           src="assets/logo/StromWise.png"
           alt="image">
      <div class="hero-actions">
        <div class="hero-actions-autocomplete">
          <mat-form-field class="search-input">
            <input
              #searchInput
              matInput
              type="search"
              autocomplete="off"
              (keyup.enter)="onHeroActionSearch(searchInput.value)"
            >
            <mat-icon matSuffix color="primary">search</mat-icon>
          </mat-form-field>
        </div>
        <st-hero-action-buttons></st-hero-action-buttons>
      </div>
    </div>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  constructor(private toolbarApiService: ToolbarApiService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(false);
  }

  onHeroActionSearch(value: string): void {
    if (value) {
      this.router.navigateByUrl(`questions/${value.toLowerCase()}`);
    }
  }
}
