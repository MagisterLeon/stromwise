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
          <div class="hero-actions-autocomplete">
            <span class="hero-actions-autocomplete-description">I want to learn</span>
            <st-hero-actions-autocomplete></st-hero-actions-autocomplete>
          </div>
          <div>
            <span class="hero-actions-buttons-description">Popular:</span>
            <button class="hero-action-button" mat-stroked-button color="custom-grey">Programming</button>
            <button class="hero-action-button" mat-stroked-button color="custom-grey">Web design</button>
            <button class="hero-action-button" mat-stroked-button color="custom-grey">Guitar</button>
            <button class="hero-action-button" mat-stroked-button color="custom-grey">Filmmaking</button>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent {

}
