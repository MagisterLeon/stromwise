import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'st-contact-map',
  template: `
    <div class="contact-right">
      <div class="contact-address">
        <h3>Poznań</h3>
        <a
          href="https://www.google.com/maps/place/Muzeum+Historii+Miasta+Poznania/@52.4085868,16.9318455,17z/data=!3m1!4b1!4m5!3m4!1s0x47045b475374faa3:0x65f5d6fb2b0a510b!8m2!3d52.4085868!4d16.9340342">Stary
          Rynek 1, 00-000 Poznań ALBO TU BYM WJEBAL GOOGLE MAPE ZEBY LADNIE WYPELNILO WOLNA PRZESTRZEN XD</a>
        <a href="tel:48123456789">(+48) 123 456 789</a>
      </div>
    </div>
  `,
  styleUrls: ['./contact-map.component.scss']
})
export class ContactMapComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
