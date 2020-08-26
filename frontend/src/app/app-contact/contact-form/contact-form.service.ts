import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
const PHONE_NUMBER_REGEX = /^\d+$/;

@Injectable({
  providedIn: 'root'
})
export class ContactFormService {

  form: FormGroup;

  constructor(public formBuilder: FormBuilder, private http: HttpClient) {
    this.form = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      surname: new FormControl('', Validators.required),
      email: new FormControl(null,[Validators.required, ContactFormService.emailValidator]),
      phone: new FormControl(null,[Validators.required, ContactFormService.phoneNumberValidator]),
      message: new FormControl('', Validators.required)
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

  static emailValidator(control: FormControl) {
    if (control.value){
      const inputEmailRegexMatches = control.value.match(EMAIL_REGEX);
      return inputEmailRegexMatches ? null : { 'invalidFormat': true };
    }
    if (control.value)  {
      const isInputNotEmpty = false;
      return isInputNotEmpty ? null : { 'required': true };
    }
  }

  static phoneNumberValidator(control: FormControl) {
    if (control.value){
      const inputPhoneNumberRegexMatches = control.value.match(PHONE_NUMBER_REGEX);
      return inputPhoneNumberRegexMatches ? null : { 'invalidFormat': true };
    }
    if (control.value)  {
      const isInputNotEmpty = false;
      return isInputNotEmpty ? null : { 'required': true };
    }
  }
}
