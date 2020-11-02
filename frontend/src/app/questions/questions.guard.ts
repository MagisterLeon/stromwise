import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {QuestionsStore} from './questions-store.service';

@Injectable({
  providedIn: 'root'
})
export class QuestionsGuard implements CanActivate {

  constructor(private questionsStore: QuestionsStore,
              private router: Router) {
  }

  canActivate(): boolean {
    if (this.questionsStore.hasAllResponses()) {
      return true;
    } else {
      this.router.navigateByUrl('');
      return false;
    }
  }

}
