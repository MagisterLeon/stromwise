import {AfterViewInit, ChangeDetectionStrategy, Component, NgZone, OnInit} from '@angular/core';
import {SkillTreeAmchartsChartBuilderService} from '../amcharts/skill-tree-amcharts-chart-builder.service';
import {SkillTreeService} from './skill-tree.service';


@Component({
  selector: 'st-skilltree',
  template: `
    <div id="skill-tree" class="skill-tree"></div>
    <st-google-search class="google-search"></st-google-search>
  `,
  styleUrls: ['./skill-tree.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SkillTreeComponent implements OnInit, AfterViewInit {
  constructor(private zone: NgZone,
              private chartBuilder: SkillTreeAmchartsChartBuilderService,
              private skillTreeService: SkillTreeService) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.zone.runOutsideAngular(() => {
      this.skillTreeService.tree().subscribe(skillTree => {
        const chart = this.chartBuilder.buildChart(
          'skill-tree',
          skillTree.children,
          nodeSelected => console.log(nodeSelected)
        );
      });
    });
  }
}
