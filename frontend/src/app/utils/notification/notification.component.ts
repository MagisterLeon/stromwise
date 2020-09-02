import { Component, OnInit } from '@angular/core';
import {NotificationService} from "./notification.service";

@Component({
  selector: 'app-notification',
  template: `
    <mat-label (click)="showNotification('fgfg', 'UNDO')">Open skack bar</mat-label>
  `,
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

  constructor(private snackBarService: NotificationService) { }

  ngOnInit(): void {}

  public showNotification(message:string, action:string) {
    this.snackBarService.openSnackBar(message, action);
  }

}
