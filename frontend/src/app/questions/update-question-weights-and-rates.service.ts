import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

interface UpdateQuestionWeightsAndRates {
  knownQuestionPublicIds: string[];
  notSureQuestionPublicIds: string[];
  notKnowQuestionPublicIds: string[];
}

@Injectable({
  providedIn: 'root'
})
export class UpdateQuestionWeightsAndRatesService {

  constructor(private httpClient: HttpClient) {
  }

  update(request: UpdateQuestionWeightsAndRates): Observable<unknown> {
    return this.httpClient.patch('/api/v1/questions/weights-and-rates', request);
  }
}
