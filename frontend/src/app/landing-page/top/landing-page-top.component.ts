import {Component} from '@angular/core';

@Component({
  selector: 'st-landing-page-top',
  template: `
    <div class="landing-page">
      <div class="hero-container">
        <div class="hero-title">Uncover Unknown Unknowns</div>
        <div class="hero-text">All human knowledge should be free. Stromwise is here to help you master it through the
          best learning pathway.
        </div>
        <div class="hero-actions">
          <div class="hero-actions-autocomplete">
            <span class="hero-actions-autocomplete-description">I want to learn</span>
            <st-hero-actions-autocomplete></st-hero-actions-autocomplete>
          </div>
          <st-hero-action-buttons></st-hero-action-buttons>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./landing-page-top.component.scss']
})
export class LandingPageTopComponent {

}
