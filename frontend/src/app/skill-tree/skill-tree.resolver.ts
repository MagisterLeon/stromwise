import {Observable} from 'rxjs';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {SkillTreeNodeModel} from './skill-tree-node.model';
import {SkillTreeService} from './skill-tree.service';

@Injectable({ providedIn: 'root' })
export class SkillTreeResolver implements Resolve<SkillTreeNodeModel> {
  constructor(private skillTreeService: SkillTreeService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.skillTreeService.tree(route.paramMap.get('name'));
  }
}
