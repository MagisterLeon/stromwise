import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {ToolbarSize} from './toolbar-size.enum';

@Injectable({
  providedIn: 'root'
})
export class ToolbarApiService {
  private $toolbarSize: BehaviorSubject<ToolbarSize> = new BehaviorSubject<ToolbarSize>(ToolbarSize.BIG);

  setToolbarSize(size: ToolbarSize): void {
    this.$toolbarSize.next(size);
  }

  getToolbarSize(): Observable<ToolbarSize> {
    return this.$toolbarSize.asObservable();
  }

  scrollToContactForm() {
    document.querySelector('st-contact-form').scrollIntoView({ behavior: 'smooth', block: 'center' });
  }
}
