import {AfterViewInit, Component, NgZone, OnInit} from '@angular/core';
import {SkillTreeAmchartsChartBuilderService} from '../amcharts/skill-tree-amcharts-chart-builder.service';
import {SkillTreeService} from './skill-tree.service';


@Component({
  selector: 'st-skilltree',
  template: `
    <div id="skill-tree" style="width: 50%; height: 500px"></div>
  `,
  styleUrls: ['./skill-tree.component.scss']
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
          skillTree.children
        );
      });
    });
  }
}
