import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Question} from './question';

@Injectable({
  providedIn: 'root'
})
export class GetQuestionsService {

  constructor(private httpClient: HttpClient) {
  }

  get(categoryName: string): Observable<Question[]> {
    return this.httpClient.get<Question[]>(`/api/v1/questions/categories/${categoryName}`);
  }
}
