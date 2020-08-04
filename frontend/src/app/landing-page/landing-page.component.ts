import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-landing-page.component.ts',
  template: `
    <st-landing-page-top></st-landing-page-top>
    <st-about-us></st-about-us>
  `,
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
