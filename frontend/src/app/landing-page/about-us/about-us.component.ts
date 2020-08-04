import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-about-us',
  template: `
    <div class="about-us">
      <div class="title">About us</div>
      <div class="divider"></div>
      <div class="description">
        <span class="description-text">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi volutpat quam fermentum,
        aliquam tortor in, semper risus. Nunc ac elementum leo. Sed iaculis, enim at blandit hendrerit, ante leo
        accumsan elit, a maximus erat magna sit amet tortor. Donec tincidunt justo nec sapien sagittis, eu consequat
        neque tincidunt. Duis nulla dui, egestas sed mi et, ornare venenatis libero. Suspendisse interdum nec erat in
        fermentum. Aliquam erat volutpat.
        </span>
        <button class="learn-more-button" mat-stroked-button>
          Learn more
          <mat-icon matSuffix>remove
          </mat-icon>
        </button>
      </div>
    </div>
  `,
  styleUrls: ['./about-us.component.scss']
})
export class AboutUsComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
