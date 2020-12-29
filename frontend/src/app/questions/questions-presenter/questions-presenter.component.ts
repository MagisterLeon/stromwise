import {Component, OnInit, ViewChild} from '@angular/core';
import {CarouselComponent} from '../../components/carousel/carousel.component';
import {GetQuestionsService} from '../get-questions.service';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {Observable} from 'rxjs';
import {Question} from '../question';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../utils/notification/notification.service';
import {QuestionResponse} from '../question-response';
import {QuestionsStore} from '../questions-store.service';
import {tap} from 'rxjs/operators';
import {UpdateQuestionResponsesService} from '../update-question-responses.service';


@Component({
  selector: 'st-questions-presenter',
  template: `
    <ng-container *ngIf="questions$ | async as questions; else spinner">
      <div class="question-header">
        <button mat-icon-button (click)="carousel.prev()">
          <mat-icon>keyboard_arrow_left</mat-icon>
        </button>
        <span class="mat-body-strong">{{carousel.getCurrentSlide() + 1}} / {{questions.length}}</span>
        <button mat-icon-button (click)="onRightClick(questions[carousel.getCurrentSlide()].question)">
          <mat-icon>keyboard_arrow_right</mat-icon>
        </button>
      </div>
      <carousel #carousel>
        <ng-container *ngFor="let question of questions;">
          <ng-container *carouselItem>
            <st-questions-preview [questionModel]="question"
                                  (questionPreviewResponse)="onQuestionResponse($event)">
            </st-questions-preview>
          </ng-container>
        </ng-container>
      </carousel>
      <button *ngIf="showDoneButton()"
              (click)="onDoneClick()"
              class="done-button" mat-fab color="accent">
        <mat-icon>done</mat-icon>
      </button>
    </ng-container>
    <ng-template #spinner>
      <mat-spinner class="spinner"></mat-spinner>
    </ng-template>
  `,
  styleUrls: ['./questions-presenter.component.scss']
})
export class QuestionsPresenterComponent implements OnInit {
  @ViewChild('carousel') carousel: CarouselComponent;

  questions$: Observable<Question[]>;

  constructor(private getQuestionsService: GetQuestionsService,
              private updateQuestionResponsesService: UpdateQuestionResponsesService,
              private toolbarApiService: ToolbarApiService,
              private questionResponseStore: QuestionsStore,
              private router: Router,
              private route: ActivatedRoute,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.questionResponseStore.clear();
    const category = this.route.snapshot.paramMap.get('category');
    this.questionResponseStore.category = category;
    this.questions$ = this.getQuestionsService.get(category)
      .pipe(
        tap(questions => this.questionResponseStore.questions = questions)
      );
    this.toolbarApiService.setIsVisible(true);
    this.toolbarApiService.setCategory(category);
  }

  onRightClick(question: string): void {
    if (this.questionResponseStore.getResponse(question)) {
      this.carousel.next();
    } else if (!this.carousel.isLastSlide()) {
      this.notificationService.showError('You need to select an option from toggle below before ' +
        'going further', 3_000);
    }
  }

  onQuestionResponse({question, questionResponseType}: QuestionResponse): void {
    this.questionResponseStore.addResponse(question, questionResponseType);
    this.carousel.next();
  }

  showDoneButton(): boolean {
    return this.questionResponseStore.hasAllResponses();
  }

  onDoneClick(): void {
    const request = {
      category: this.questionResponseStore.category,
      knownQuestions: this.questionResponseStore.getKnownQuestions().map(q => q.question),
      notSureQuestions: this.questionResponseStore.getNotSureQuestions().map(q => q.question),
      notKnowQuestions: this.questionResponseStore.getNotKnowQuestions().map(q => q.question)
    };
    this.updateQuestionResponsesService.update(request).subscribe();
    this.router.navigateByUrl('unknown-preview');
  }
}

