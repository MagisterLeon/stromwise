import {Component, OnInit} from '@angular/core';
import {ToolbarApiService} from '../../app-toolbar/toolbar-api.service';
import {GetQuestionsService} from '../get-questions.service';

@Component({
  selector: 'st-questions-preview',
  template: `
    <div class="question-header">
      <button mat-icon-button>
        <mat-icon>keyboard_arrow_left</mat-icon>
      </button>
      <span class="mat-body-strong">1 / 10</span>
      <button mat-icon-button>
        <mat-icon>keyboard_arrow_right</mat-icon>
      </button>
    </div>
    <span class="mat-body" matBadge
          matBadgeColor="accent"
          matBadgeOverlap="false"
          matBadgeIcon="help">Dokąd nocą tupta jeż?</span>
    <mat-button-toggle-group class="" name="answer" vertical>
      <mat-button-toggle class="know mat-body-2" value="know">Know</mat-button-toggle>
      <mat-button-toggle class="not-sure mat-body-2" value="notSure">Not sure</mat-button-toggle>
      <mat-button-toggle class="not-know mat-body-2" value="notKnow">Don't know</mat-button-toggle>
    </mat-button-toggle-group>
  `,
  styleUrls: ['./questions-preview.component.scss']
})
export class QuestionsPreviewComponent implements OnInit {

  constructor(private getQuestionsService: GetQuestionsService,
              private toolbarApiService: ToolbarApiService) {
  }

  ngOnInit(): void {
    this.toolbarApiService.setIsVisible(true);
    this.toolbarApiService.setCategory('Programming');


  }
}
