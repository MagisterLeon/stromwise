import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {Question} from '../question';
import {MatButtonToggleChange} from '@angular/material/button-toggle';
import {QuestionResponse} from '../question-response';

@Component({
  selector: 'st-questions-preview',
  template: `
    <div class="question">
       <span class="mat-body">{{questionModel.question}}</span>
    </div>
    <mat-button-toggle-group name="answer" vertical (change)="onQuestionPreviewResponseChange($event)">
      <mat-button-toggle class="know mat-body-2" value="KNOW">Know</mat-button-toggle>
      <mat-button-toggle class="not-sure mat-body-2" value="NOT_SURE">Not sure</mat-button-toggle>
      <mat-button-toggle class="not-know mat-body-2" value="DONT_KNOW">Don't know</mat-button-toggle>
    </mat-button-toggle-group>
  `,
  styleUrls: ['./questions-preview.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class QuestionsPreviewComponent {

  @Input() questionModel: Question;
  @Output() questionPreviewResponse: EventEmitter<QuestionResponse> = new EventEmitter<QuestionResponse>();

  onQuestionPreviewResponseChange(event: MatButtonToggleChange): void {
    this.questionPreviewResponse.emit({
      question: this.questionModel.question,
      questionResponseType: event.value
    });
  }
}
