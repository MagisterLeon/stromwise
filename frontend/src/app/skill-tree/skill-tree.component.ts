import {AfterViewInit, ChangeDetectionStrategy, Component, NgZone, OnInit} from '@angular/core';
import {SkillTreeAmchartsChartBuilderService} from '../amcharts/skill-tree-amcharts-chart-builder.service';
import {SkillTreeState} from './skill-tree.state';
import {ActivatedRoute} from '@angular/router';
import {ToolbarApiService} from '../app-toolbar/toolbar-api.service';
import {ToolbarSize} from '../app-toolbar/toolbar-size.enum';


@Component({
  selector: 'st-skilltree',
  template: `
    <div class="skilltree-container">
      <div id="skill-tree"></div>
      <st-google-search class="google-search"></st-google-search>
    </div>
  `,
  styleUrls: ['./skill-tree.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SkillTreeComponent implements OnInit, AfterViewInit {

  constructor(private zone: NgZone,
              private chartBuilder: SkillTreeAmchartsChartBuilderService,
              private skillTreeState: SkillTreeState,
              private toolbarApi: ToolbarApiService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.toolbarApi.setToolbarSize(ToolbarSize.COMPACT);
  }

  ngAfterViewInit(): void {
    this.zone.runOutsideAngular(() => {
      this.chartBuilder.buildChart(
        'skill-tree',
        this.route.snapshot.data.skilltree.children,
        node => this.skillTreeState.nodeSelected$.next(node)
      );
    });
  }
}
