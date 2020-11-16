import {Component, OnInit} from '@angular/core';
import {QuestionsStore} from '../questions-store.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {Question} from '../question';
import {UpdateQuestionWeightsAndRatesService} from '../update-question-weights-and-rates.service';
import {Router} from '@angular/router';

@Component({
  selector: 'st-questions-known-sorter',
  template: `
    <div class="mat-h4">Sort the questions in order of importance</div>
    <div cdkDropList class="questions-list" (cdkDropListDropped)="drop($event)">
      <div class="questions-item"
           *ngFor="let question of knownQuestions; let i = index" cdkDrag>
        {{i + 1}}. {{question.question}}
      </div>
    </div>
    <button class="confirm-button" (click)="onConfirmClick()"
            mat-flat-button>Confirm
    </button>
  `,
  styleUrls: ['./questions-known-sorter.component.scss']
})
export class QuestionsKnownSorterComponent implements OnInit {

  knownQuestions: Question[];

  constructor(private questionsStore: QuestionsStore,
              private updateQuestionWeightsService: UpdateQuestionWeightsAndRatesService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.knownQuestions = this.questionsStore.getKnownQuestions();
  }

  drop(event: CdkDragDrop<Question[]>): void {
    moveItemInArray(this.questionsStore.questions, event.previousIndex, event.currentIndex);
    this.knownQuestions = this.questionsStore.getKnownQuestions();
  }

  onConfirmClick(): void {
    const request = {
      knownQuestionPublicIds: this.knownQuestions.map(q => q.publicId),
      notSureQuestionPublicIds: this.questionsStore.getNotSureQuestions().map(q => q.publicId),
      notKnowQuestionPublicIds: this.questionsStore.getNotKnowQuestions().map(q => q.publicId),
    };

    this.updateQuestionWeightsService.update(request)
      .subscribe(() => this.router.navigateByUrl('unknown-preview'));
  }
}
