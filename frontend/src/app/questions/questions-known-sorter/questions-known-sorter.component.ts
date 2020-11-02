import {Component, OnInit} from '@angular/core';
import {QuestionsStore} from '../questions-store.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {Question} from '../question';
import {UpdateQuestionWeightsService} from '../update-question-weights.service';

@Component({
  selector: 'st-questions-known-sorter',
  template: `
    <div class="mat-h4">Sort the questions in order of importance</div>
    <div cdkDropList class="questions-list" (cdkDropListDropped)="drop($event)">
      <div class="questions-item"
           *ngFor="let question of questionsStore.getKnownQuestions(); let i = index" cdkDrag>
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

  constructor(public questionsStore: QuestionsStore,
              private updateQuestionWeightsService: UpdateQuestionWeightsService) {
  }

  ngOnInit(): void {
  }

  drop(event: CdkDragDrop<Question[]>): void {
    moveItemInArray(this.questionsStore.questions, event.previousIndex, event.currentIndex);
  }

  onConfirmClick(): void {
    const questionPublicIds = this.questionsStore.questions.map(q => q.publicId);
    this.updateQuestionWeightsService.update(questionPublicIds)
      .subscribe(() => console.log('weights updated'));
  }
}
