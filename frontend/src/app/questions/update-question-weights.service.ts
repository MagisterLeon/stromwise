import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UpdateQuestionWeightsService {

  constructor(private httpClient: HttpClient) {
  }

  update(questionPublicIds: string[]): Observable<unknown> {
    return this.httpClient.patch('/api/v1/questions/weights', {questionPublicIds});
  }
}
