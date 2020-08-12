import {AfterViewInit, ChangeDetectionStrategy, Component, NgZone} from '@angular/core';
import {SkillTreeAmchartsChartBuilderService} from '../amcharts/skill-tree-amcharts-chart-builder.service';
import {SkillTreeState} from './skill-tree.state';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'st-skilltree',
  template: `
    <div id="skill-tree" class="skill-tree"></div>
    <st-google-search class="google-search"></st-google-search>
  `,
  styleUrls: ['./skill-tree.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SkillTreeComponent implements AfterViewInit {

  constructor(private zone: NgZone,
              private chartBuilder: SkillTreeAmchartsChartBuilderService,
              private skillTreeState: SkillTreeState,
              private route: ActivatedRoute) {
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
