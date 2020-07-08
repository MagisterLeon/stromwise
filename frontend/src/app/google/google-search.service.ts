import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {GoogleSearchResultModel} from './google-search-result.model';

@Injectable({
  providedIn: 'root'
})
export class GoogleSearchService {

  constructor(private httpClient: HttpClient) {
  }

  get(query: string): Observable<GoogleSearchResultModel[]> {
    return this.httpClient.get<GoogleSearchResultModel[]>(`/api/skill-tree/v1/google/${query}`);
  }
}
