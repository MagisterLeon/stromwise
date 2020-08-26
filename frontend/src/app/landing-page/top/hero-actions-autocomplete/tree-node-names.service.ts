import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TreeNodeNamesService {

  constructor(private httpClient: HttpClient) {
  }

  nodeNames(): Observable<string[]> {
    return this.httpClient.get<string[]>(`/api/skill-tree/v1/nodes/names`);
  }

}
