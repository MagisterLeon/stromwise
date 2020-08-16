import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-contact',
  template: `
    <st-contact-form></st-contact-form>
    <st-contact-map></st-contact-map>
  `,
  styleUrls: ['./app-contact.component.scss']
})
export class AppContactComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
