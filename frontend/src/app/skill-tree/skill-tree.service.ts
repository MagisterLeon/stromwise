import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SkillTreeNode} from './skill-tree-node';

@Injectable({
  providedIn: 'root'
})
export class SkillTreeService {

  constructor(private httpClient: HttpClient) {
  }

  tree(): Observable<SkillTreeNode> {
    return this.httpClient.get<SkillTreeNode>('/api/skill-tree/v1/tree');
  }

}
