import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Question} from './question';

@Injectable({
  providedIn: 'root'
})
export class GetQuestionsWithResponseRatesService {

  constructor(private httpClient: HttpClient) {
  }

  get(questions: string[]): Observable<Question[]> {
    const params = new HttpParams().set('questions', questions.join(','));
    return this.httpClient.post<Question[]>(`/api/v1/questions/`, {params});
  }
}
