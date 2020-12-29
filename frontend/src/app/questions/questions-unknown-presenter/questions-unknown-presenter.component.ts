import {Component, OnDestroy, OnInit, Renderer2, ViewChild} from '@angular/core';
import {CarouselComponent} from '../../components/carousel/carousel.component';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {Question} from '../question';
import {QuestionsStore} from '../questions-store.service';
import {Router} from '@angular/router';


@Component({
  selector: 'st-questions-unknown-presenter',
  template: `
    <ng-container>
      <div class="question-header">
        <button mat-icon-button (click)="carousel.prev()">
          <mat-icon>keyboard_arrow_left</mat-icon>
        </button>
        <span class="mat-body-strong">{{carousel.getCurrentSlide() + 1}} / {{unknownQuestions.length}}</span>
        <button mat-icon-button (click)="carousel.next()">
          <mat-icon>keyboard_arrow_right</mat-icon>
        </button>
      </div>
      <carousel #carousel>
        <ng-container *ngFor="let question of unknownQuestions;">
          <ng-container *carouselItem>
            <st-questions-unknown-preview [questionModel]="question"></st-questions-unknown-preview>
          </ng-container>
        </ng-container>
      </carousel>
      <button (click)="onDoneClick()"
              class="done-button" mat-fab color="accent">
        <mat-icon>done</mat-icon>
      </button>
    </ng-container>
  `,
  styleUrls: ['./questions-unknown-presenter.component.scss'],
})
export class QuestionsUnknownPresenterComponent implements OnInit, OnDestroy {
  @ViewChild('carousel') carousel: CarouselComponent;

  unknownQuestions: Question[];

  private category: string;

  constructor(private toolbarApiService: ToolbarApiService,
              private questionResponseStore: QuestionsStore,
              private router: Router,
              private renderer: Renderer2) {
  }

  ngOnInit(): void {
    this.unknownQuestions = this.questionResponseStore.getUnknownQuestions();
    this.category = this.questionResponseStore.category;
    this.questionResponseStore.clear();
    this.toolbarApiService.setIsVisible(true);

    this.renderer.setStyle(document.body.parentElement, 'height', 'auto');
    this.renderer.setStyle(document.body, 'height', 'auto');
  }

  ngOnDestroy(): void {
    this.renderer.setStyle(document.body.parentElement, 'height', '100%');
    this.renderer.setStyle(document.body, 'height', '100%');
  }

  onDoneClick(): void {
    this.router.navigateByUrl(`questions/${this.category}`);
  }
}

