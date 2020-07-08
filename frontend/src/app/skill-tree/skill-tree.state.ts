import {Injectable} from '@angular/core';
import {SkillTreeNode} from './skill-tree-node';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillTreeState {

  nodeSelected$: Subject<SkillTreeNode>;
}
