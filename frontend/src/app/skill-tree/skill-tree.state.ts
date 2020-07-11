import {Injectable} from '@angular/core';
import {SkillTreeNodeModel} from './skill-tree-node.model';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillTreeState {

  public nodeSelected$: Subject<SkillTreeNodeModel> = new Subject<SkillTreeNodeModel>();

  public nodeSelected(): Observable<SkillTreeNodeModel> {
    return this.nodeSelected$.asObservable();
  }
}

