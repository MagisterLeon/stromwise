import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'st-contact-form',
  template: `
    <div>
      <h1>Contact with us</h1>
      <form class="contact-form">
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>name</mat-label>
            <input matInput formControlName="name" required>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>surname</mat-label>
            <input matInput formControlName="phone" required>
          </mat-form-field>
        </div>
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>email</mat-label>
            <input matInput formControlName="company" required>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>phone</mat-label>
            <input matInput formControlName="email" required>
          </mat-form-field>
        </div>
        <mat-form-field appearance="outline">
          <mat-label>message</mat-label>
          <textarea matInput formControlName="message" required></textarea>
        </mat-form-field>
        <button mat-button>Send</button>
      </form>
    </div>
  `,
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
