import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToolbarApiService {
  private $isVisible: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private $category: BehaviorSubject<string> = new BehaviorSubject<string>('');

  setIsVisible(isVisible: boolean): void {
    this.$isVisible.next(isVisible);
  }

  setCategory(category: string): void {
    this.$category.next(category);
  }

  isVisible(): Observable<boolean> {
    return this.$isVisible.asObservable();
  }

  getCategory(): Observable<string> {
    return this.$category.asObservable();
  }
}
