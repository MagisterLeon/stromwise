import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-hero-action-buttons',
  template: `
    <div>
      <span class="hero-actions-buttons-description">Popular:</span>
      <a routerLink="/skilltree/Programming">
        <button class="hero-action-button" mat-stroked-button>Programming</button>
      </a>
      <a routerLink="/skilltree/Web design">
        <button class="hero-action-button" mat-stroked-button>Web design</button>
      </a>
      <a routerLink="/skilltree/Guitar">
        <button class="hero-action-button" mat-stroked-button>Guitar</button>
      </a>
      <a routerLink="/skilltree/Filmmaking">
        <button class="hero-action-button" mat-stroked-button>Filmmaking</button>
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
