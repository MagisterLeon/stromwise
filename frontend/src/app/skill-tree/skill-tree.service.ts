import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SkillTreeNodeModel} from './skill-tree-node.model';

@Injectable({
  providedIn: 'root'
})
export class SkillTreeService {

  constructor(private httpClient: HttpClient) {
  }

  tree(name: string): Observable<SkillTreeNodeModel> {
    return this.httpClient.get<SkillTreeNodeModel>(`/api/skill-tree/v1/nodes/${name}`);
  }

}
