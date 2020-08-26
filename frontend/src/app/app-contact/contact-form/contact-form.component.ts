import {Component, OnInit} from '@angular/core';
import {ContactFormService} from "./contact-form.service";


@Component({
  selector: 'st-contact-form',
  template: `
    <div>
      <h1>Contact with us</h1>
      <form class="contact-form" [formGroup]="contactFormService.form" (ngSubmit)="contactFormService.onSubmit()">
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>name</mat-label>
            <input matInput formControlName="name" required>
            <mat-error *ngIf="contactFormService.hasError('name', 'required')">Name is required</mat-error>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>surname</mat-label>
            <input matInput formControlName="surname" required>
            <mat-error *ngIf="contactFormService.hasError('surname', 'required')">Surname is required</mat-error>
          </mat-form-field>
        </div>
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>email</mat-label>
            <input matInput formControlName="email"
                   pattern="/^[a-zA-Z0-9.!#$%&’*+/=?^_\`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/;" required>
            <mat-error *ngIf="contactFormService.form.get('email').hasError('required')">Email is required</mat-error>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>phone</mat-label>
            <input matInput formControlName="phone" required>
            <mat-error *ngIf="contactFormService.hasError('phone', 'required')">Phone is required</mat-error>
          </mat-form-field>
        </div>
        <mat-form-field class="message" appearance="outline">
          <mat-label>message</mat-label>
          <textarea matInput formControlName="message" required></textarea>
          <mat-error *ngIf="contactFormService.hasError('message', 'required')">Message is required</mat-error>
        </mat-form-field>
        <button mat-button>Send</button>
      </form>
    </div>
  `,
  styleUrls: ['./contact-form.component.scss'],
  providers: [ContactFormService]
})
export class ContactFormComponent implements OnInit {

  constructor(public contactFormService: ContactFormService) {
    this.contactFormService.onSubmit();
    this.contactFormService.hasError('message', 'required');
  }

  ngOnInit(): void {}
}
