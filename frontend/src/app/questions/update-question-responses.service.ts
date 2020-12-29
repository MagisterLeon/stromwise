import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

interface UpdateQuestionResponses {
  category: string;
  knownQuestions: string[];
  notSureQuestions: string[];
  notKnowQuestions: string[];
}

@Injectable({
  providedIn: 'root'
})
export class UpdateQuestionResponsesService {

  constructor(private httpClient: HttpClient) {
  }

  update(request: UpdateQuestionResponses): Observable<unknown> {
    return this.httpClient.put('/api/v1/questions/responses', request);
  }
}
