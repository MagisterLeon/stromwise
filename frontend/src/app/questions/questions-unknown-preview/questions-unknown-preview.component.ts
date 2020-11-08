import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../question';
import {ChartType} from 'angular-google-charts';

@Component({
  selector: 'st-questions-unknown-preview',
  template: `
    <ng-container *ngIf="questionModel">
      <div>{{questionModel.question}}</div>
      <div>{{questionModel.answer}}</div>
      <google-chart #chart
                    [type]="type"
                    [data]="data"
                    [options]="options">
      </google-chart>
      <st-google-search [question]="questionModel.question"></st-google-search>
    </ng-container>
  `,
  styleUrls: ['./questions-unknown-preview.component.scss']
})
export class QuestionsUnknownPreviewComponent implements OnInit {

  @Input() questionModel: Question;

  type = ChartType.PieChart;
  data;
  options = {
    pieSliceText: 'label',
    colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'],
    width: 200,
    height: 200,
    backgroundColor: 'transparent',
    legend: {position: 'none'}
  };

  constructor() {
  }

  ngOnInit(): void {
    this.data = [
      ['don`t know', this.questionModel.dontKnow],
      ['not sure', this.questionModel.notSure],
      ['know', this.questionModel.know],
    ];
  }
}
