import {Injectable} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import {NotificationService} from '../utils/notification/notification.service';

@Injectable({
  providedIn: 'root'
})
export class AddCategoryService {

  form: FormGroup;

  constructor(public formBuilder: FormBuilder, private http: HttpClient, private snackBar: MatSnackBar,
              private notificationService: NotificationService) {
    this.form = this.formBuilder.group({
      categoryName: new FormControl('', Validators.required),
      question: new FormControl('', Validators.required),
      answer: new FormControl('')
    });
  }

  public onSubmit(): void {

    const request = {
      categories: [this.form.get('categoryName').value],
      question: this.form.get('question').value,
      answer: this.form.get('answer').value
    };

    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    this.http
      .post('/api/v1/questions', request)
      .subscribe(
        () => {
          this.notificationService.showSuccess('Category with question added');
        },
        () => {
          this.notificationService.showError('There is a problem with adding a category');
        }
      );
  }
}
