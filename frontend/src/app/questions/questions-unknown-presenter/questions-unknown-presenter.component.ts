import {Component, OnDestroy, OnInit, Renderer2, ViewChild} from '@angular/core';
import {CarouselComponent} from '../../components/carousel/carousel.component';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {Observable} from 'rxjs';
import {Question} from '../question';
import {QuestionsStore} from '../questions-store.service';
import {GetQuestionsWithResponseRatesService} from '../get-questions-with-response-rates.service';


@Component({
  selector: 'st-questions-unknown-presenter',
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
            <st-questions-unknown-preview [questionModel]="question"></st-questions-unknown-preview>
          </ng-container>
        </ng-container>
      </carousel>
    </ng-container>
  `,
  styleUrls: ['./questions-unknown-presenter.component.scss'],
})
export class QuestionsUnknownPresenterComponent implements OnInit, OnDestroy {
  @ViewChild('carousel') carousel: CarouselComponent;

  questions$: Observable<Question[]>;

  constructor(private getQuestionsService: GetQuestionsWithResponseRatesService,
              private toolbarApiService: ToolbarApiService,
              private questionResponseStore: QuestionsStore,
              private renderer: Renderer2) {
  }

  ngOnInit(): void {
    const publicIds = this.questionResponseStore.getUnknownQuestions().map(q => q.publicId);
    this.questions$ = this.getQuestionsService.get(publicIds);
    this.toolbarApiService.setIsVisible(true);

    this.renderer.setStyle(document.body.parentElement, 'height', 'auto');
    this.renderer.setStyle(document.body, 'height', 'auto');
  }

  ngOnDestroy(): void {
    this.renderer.setStyle(document.body.parentElement, 'height', '100%');
    this.renderer.setStyle(document.body, 'height', '100%');
  }
}

