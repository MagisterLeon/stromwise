import {Component} from '@angular/core';

@Component({
  selector: 'st-landing-page',
  template: `
    <div class="landing-page">
      <div class="hero-container">
        <div class="hero-title">Uncover Unknown Unknowns</div>
        <div class="hero-text">All human knowledge should be free. Stromwise is here to help you master it through the
          best learning pathway.
        </div>
        <div class="hero-actions">
          <span class="hero-actions-prefix">I want to learn</span>
          <st-hero-actions-autocomplete></st-hero-actions-autocomplete>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent {

}
