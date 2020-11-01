import {Component, OnInit, ViewChild} from '@angular/core';
import {CarouselComponent} from '../../components/carousel/carousel.component';
import {GetQuestionsService} from '../get-questions.service';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {Observable} from 'rxjs';
import {Question} from '../question';
import {ActivatedRoute} from '@angular/router';
import {NotificationService} from '../../utils/notification/notification.service';


@Component({
  selector: 'st-questions-presenter',
  template: `
    <ng-container *ngIf="questions$ | async as questions">
      <div class="question-header">
        <button mat-icon-button (click)="carousel.prev()">
          <mat-icon>keyboard_arrow_left</mat-icon>
        </button>
        <span class="mat-body-strong">{{carousel.getCurrentSlide() + 1}} / {{questions.length}}</span>
        <button mat-icon-button (click)="onRightClick()">
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

  questions$: Observable<Question[]>;

  constructor(private getQuestionsService: GetQuestionsService,
              private toolbarApiService: ToolbarApiService,
              private route: ActivatedRoute,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    const category = this.route.snapshot.paramMap.get('category');
    this.questions$ = this.getQuestionsService.get(category);
    this.toolbarApiService.setIsVisible(true);
    this.toolbarApiService.setCategory(category);
  }

  onRightClick(): void {
    if (true) {
      this.notificationService.showError('You need to select an option from toggle below before ' +
        'going further', 3_000);
    } else {
      this.carousel.next();
    }
  }
}

