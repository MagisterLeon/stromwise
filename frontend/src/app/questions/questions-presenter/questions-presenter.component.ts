import {Component, OnInit, ViewChild} from '@angular/core';
import {CarouselComponent} from '../../components/carousel/carousel.component';
import {GetQuestionsService} from '../get-questions.service';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {Observable} from 'rxjs';
import {Question} from '../question';


@Component({
  selector: 'st-questions-presenter',
  template: `
    <ng-container *ngIf="questions$ | async as questions">
      <div class="question-header">
        <button mat-icon-button (click)="carousel.prev()">
          <mat-icon>keyboard_arrow_left</mat-icon>
        </button>
        <span class="mat-body-strong">{{carousel.getCurrentSlide() + 1}} / {{questions.length}}</span>
        <button mat-icon-button (click)="carousel.next()">
          <mat-icon>keyboard_arrow_right</mat-icon>
        </button>
      </div>
      <carousel #carousel>
        <ng-container *ngFor="let question of questions;">
          <ng-container *carouselItem>
            <st-questions-preview [questionModel]="question"></st-questions-preview>
          </ng-container>
        </ng-container>
      </carousel>
    </ng-container>
  `,
  styleUrls: ['./questions-presenter.component.scss']
})
export class QuestionsPresenterComponent implements OnInit {
  @ViewChild('carousel') carousel: CarouselComponent;

  questions$: Observable<Question[]> = this.getQuestionsService.get('Programming');

  constructor(private getQuestionsService: GetQuestionsService,
              private toolbarApiService: ToolbarApiService) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(true);
    this.toolbarApiService.setCategory('Programming');
  }
}

