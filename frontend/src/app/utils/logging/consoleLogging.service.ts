import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsoleLoggingService {

  constructor() { }

  logError(message: string) {
    console.log('Console logging service: ' + message);
  }
}
