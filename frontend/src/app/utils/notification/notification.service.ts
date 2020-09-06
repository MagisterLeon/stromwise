import {Injectable, NgZone} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(
    private snackBar: MatSnackBar, private zone: NgZone) { }

  showSuccess(message: string): void {
    this.zone.run(() => {
      this.snackBar.open(message, '(ʘ‿ʘ)', {
        panelClass: ['notification-success'],
        duration: 10000,
        verticalPosition: 'top',
        horizontalPosition: 'right'
      });
    });
  }

  showError(message: string): void {
    this.zone.run(() => {
      this.snackBar.open(message, '¯\\_(ツ)_/¯', {
        panelClass: 'notification-error',
        duration: 10000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
    });
  }
}
