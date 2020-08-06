import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-footer',
  template: `
    <div class="social-media">
      <img class="social-media-icon"
           src="assets/social-media/facebook.png"
           alt="image">
      <img class="social-media-icon"
           src="assets/social-media/linkedin.png"
           alt="image">
      <img class="social-media-icon"
           src="assets/social-media/twitter.png"
           alt="image">
    </div>
    <div class="all-rights-reserved">ALL RIGHTS RESERVED - © 2020 STROMWISE</div>
  `,
  styleUrls: ['./app-footer.component.scss']
})
export class AppFooterComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
