import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

const EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

@Injectable({
  providedIn: 'root'
})
export class ContactFormService {

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


}
