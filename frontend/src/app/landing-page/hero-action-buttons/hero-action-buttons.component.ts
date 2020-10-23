import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-hero-action-buttons',
  template: `
    <div>
      <a routerLink="/questions/programming">
        <button class="hero-action-button" mat-stroked-button>Programming</button>
      </a>
      <a routerLink="/skilltree/Web design">
        <button class="hero-action-button" mat-stroked-button>Web design</button>
      </a>
      <a routerLink="/skilltree/Guitar">
        <button class="hero-action-button" mat-stroked-button>Guitar</button>
      </a>
    </div>
  `,
  styleUrls: ['./hero-action-buttons.component.scss']
})
export class HeroActionButtonsComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
