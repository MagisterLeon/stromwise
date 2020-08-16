import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'st-contact-form',
  template: `
    <div>
      <h1>Contact with us</h1>
      <form class="contact-form"[formGroup]="form">
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>name</mat-label>
            <input matInput formControlName="name" required>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>surname</mat-label>
            <input matInput formControlName="surname" required>
          </mat-form-field>
        </div>
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>email</mat-label>
            <input matInput formControlName="email" required>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>phone</mat-label>
            <input matInput formControlName="phone" required>
          </mat-form-field>
        </div>
        <mat-form-field class="message" appearance="outline">
          <mat-label>message</mat-label>
          <textarea matInput formControlName="message" required></textarea>
        </mat-form-field>
        <button mat-button (click)="onSend()">Send</button>
      </form>
    </div>
  `,
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {

  form: FormGroup;

  constructor(public formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      name: new FormControl(''),
      surname: new FormControl(''),
      email: new FormControl(''),
      phone: new FormControl(''),
      message: new FormControl('')
    })
  }

  public onSend() {
    console.log(this.form.value);
  }

  ngOnInit(): void {
  }
}
