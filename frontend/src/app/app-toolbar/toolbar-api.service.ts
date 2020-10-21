import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToolbarApiService {
  private $isVisible: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  setIsVisible(isVisible: boolean): void {
    this.$isVisible.next(isVisible);
  }

  isVisible(): Observable<boolean> {
    return this.$isVisible.asObservable();
  }
}
