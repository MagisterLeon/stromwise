import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'st-contact',
  template: `
    <div class="form-container">
      <div class="contact-left">
        <h1>Contact with us</h1>
        <div class="contact-form">
          <form id="form-left">
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>full name</mat-label>
              <input matInput formControlName="name" required>
            </mat-form-field>
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>phone</mat-label>
              <input matInput formControlName="phone" required>
            </mat-form-field>
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>company</mat-label>
              <input matInput formControlName="company" required>
            </mat-form-field>
          </form>
          <form id="form-right">
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>e-email</mat-label>
              <input matInput formControlName="email" required>
            </mat-form-field>
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>country</mat-label>
              <input matInput formControlName="country" required>
            </mat-form-field>
            <mat-form-field class="form-field" appearance="outline">
              <mat-label>city</mat-label>
              <input matInput formControlName="city" required>
            </mat-form-field>
          </form>
        </div>
        <div class="contact-message">
          <mat-form-field class="form-field" appearance="outline">
            <mat-label>message</mat-label>
            <textarea matInput formControlName="message" required></textarea>
          </mat-form-field>
        </div>
        <button mat-button>Send</button>
      </div>
      <div class="contact-right">
        <div class="contact-address">
          <h3>Poznań</h3>
          <a href="https://www.google.com/maps/place/Muzeum+Historii+Miasta+Poznania/@52.4085868,16.9318455,17z/data=!3m1!4b1!4m5!3m4!1s0x47045b475374faa3:0x65f5d6fb2b0a510b!8m2!3d52.4085868!4d16.9340342">Stary Rynek 1, 00-000 Poznań ALBO TU BYM WJEBAL GOOGLE MAPE ZEBY LADNIE WYPELNILO WOLNA PRZESTRZEN XD</a>
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
