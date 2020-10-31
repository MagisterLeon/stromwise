import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Question} from '../question';

@Component({
  selector: 'st-questions-preview',
  template: `
    <div class="question">
       <span class="mat-body" matBadge
             matBadgeColor="accent"
             matBadgeOverlap="false"
             matBadgeIcon="help">{{questionModel.question}}</span>
    </div>
    <mat-button-toggle-group name="answer" vertical>
      <mat-button-toggle class="know mat-body-2" value="know">Know</mat-button-toggle>
      <mat-button-toggle class="not-sure mat-body-2" value="notSure">Not sure</mat-button-toggle>
      <mat-button-toggle class="not-know mat-body-2" value="notKnow">Don't know</mat-button-toggle>
    </mat-button-toggle-group>
  `,
  styleUrls: ['./questions-preview.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class QuestionsPreviewComponent {

  @Input() questionModel: Question;
}
