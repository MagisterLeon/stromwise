import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from '@angular/common/http';

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

@Component({
  selector: 'st-contact-form',
  template: `
    <div>
      <h1>Contact with us</h1>
      <form class="contact-form" [formGroup]="form" (ngSubmit)="onSubmit()">
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>name</mat-label>
            <input matInput formControlName="name" required>
            <mat-error *ngIf="hasError('name', 'required')">Name is required</mat-error>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>surname</mat-label>
            <input matInput formControlName="surname" required>
            <mat-error *ngIf="hasError('surname', 'required')">Surname is required</mat-error>
          </mat-form-field>
        </div>
        <div class="contact-form-row">
          <mat-form-field appearance="outline">
            <mat-label>email</mat-label>
            <input matInput formControlName="email" pattern="/^[a-zA-Z0-9.!#$%&’*+/=?^_\`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/;" required>
            <mat-error *ngIf="form.get('email').hasError('required')">Email is required</mat-error>
          </mat-form-field>
          <mat-form-field appearance="outline">
            <mat-label>phone</mat-label>
            <input matInput formControlName="phone" required>
            <mat-error *ngIf="hasError('phone', 'required')">Phone is required</mat-error>
          </mat-form-field>
        </div>
        <mat-form-field class="message" appearance="outline">
          <mat-label>message</mat-label>
          <textarea matInput formControlName="message" required></textarea>
          <mat-error *ngIf="hasError('message', 'required')">Message is required</mat-error>
        </mat-form-field>
        <button mat-button>Send</button>
      </form>
    </div>
  `,
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {

  form: FormGroup;

  constructor(public formBuilder: FormBuilder, private http: HttpClient) {
    this.form = this.formBuilder.group({
      name: new FormControl(''),
      surname: new FormControl('', [Validators.required, Validators.email]),
      email: new FormControl(''),
      phone: new FormControl(''),
      message: new FormControl('')
    })
  }

  public onSubmit() {
    const formData: any = new FormData();
    formData.append("name", this.form.get('name').value);
    formData.append("surname", this.form.get('surname').value);
    formData.append("email", this.form.get('email').value);
    formData.append("phone", this.form.get('phone').value);
    formData.append("message", this.form.get('message').value);

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    this.http.post('/api/skill-tree/v1/contact/request', formData).subscribe((error) => console.log(error))
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.form.controls[controlName].hasError(errorName);
  }

  ngOnInit(): void {
  }
}
