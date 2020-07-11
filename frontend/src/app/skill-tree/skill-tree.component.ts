import {AfterViewInit, ChangeDetectionStrategy, Component, NgZone} from '@angular/core';
import {SkillTreeAmchartsChartBuilderService} from '../amcharts/skill-tree-amcharts-chart-builder.service';
import {SkillTreeService} from './skill-tree.service';
import {SkillTreeState} from './skill-tree.state';


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
              private skillTreeService: SkillTreeService,
              private skillTreeState: SkillTreeState) {
  }

  ngAfterViewInit(): void {
    this.zone.runOutsideAngular(() => {
      this.skillTreeService.tree().subscribe(skillTree => {
        this.chartBuilder.buildChart(
          'skill-tree',
          skillTree.children,
          node => this.skillTreeState.nodeSelected$.next(node)
        );
      });
    });
  }
}
