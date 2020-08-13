import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'st-contact',
  template: `
    <div class="contact">
      <div class="contact-left">
        <h1>Contact with us</h1>
        <div class="contact-from">
          <form id="form-left">
            <div>
              <label for="name">Full name</label>
              <input type="text" name="full-name" required>
            </div>
            <div>
              <label for="phone">Phone</label>
              <input type="text" name="phone" required>
            </div>
            <div>
              <label for="company">Company</label>
              <input type="text" name="company" required>
            </div>
          </form>
          <form id="form-right">
            <div>
              <label for="email">Email</label>
              <input type="text" name="email" required>
            </div>
            <div>
              <label for="country">Country</label>
              <input type="text" name="country" required>
            </div>
            <div>
              <label for="city">City</label>
              <input type="text" name="city" required>
            </div>
          </form>
          <div class="message">
            <label for="message">Message</label>
            <input type="text" name="message" required>
          </div>
        </div>
      </div>
      <div class="contact-right">
        <div class="contact-address">
          <h3>Poznań</h3>
          <a href="https://www.google.com/maps/place/Muzeum+Historii+Miasta+Poznania/@52.4085868,16.9318455,17z/data=!3m1!4b1!4m5!3m4!1s0x47045b475374faa3:0x65f5d6fb2b0a510b!8m2!3d52.4085868!4d16.9340342">Stary Rynek 1, 00-000 Poznań</a>
          <a href="tel:48123456789">(+48) 123 456 789</a>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./app-contact.component.scss']
})
export class AppContactComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
