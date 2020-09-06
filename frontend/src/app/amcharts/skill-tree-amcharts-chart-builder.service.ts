import * as am4core from '@amcharts/amcharts4/core';
import * as am4charts from '@amcharts/amcharts4/charts';
import {Column, TreeMapSeries} from '@amcharts/amcharts4/charts';
import am4themes_dark from '@amcharts/amcharts4/themes/dark';
import am4themes_animated from '@amcharts/amcharts4/themes/animated';
import {Injectable} from '@angular/core';
import {SkillTreeNodeModel} from '../skill-tree/skill-tree-node.model';


@Injectable({
  providedIn: 'root'
})
export class SkillTreeAmchartsChartBuilderService {

  buildChart(nodeSelector: string, data: SkillTreeNodeModel[], hitConsumer: (Object) => void): am4charts.TreeMap {
    am4core.useTheme(am4themes_dark);
    am4core.useTheme(am4themes_animated);
    const chart = this.initChart(nodeSelector, data);

    for (let i = 0; i < 10; i++) {
      const levelSeriesTemplate = chart.seriesTemplates.create(`${i}`);
      this.styleContainer(levelSeriesTemplate);
      const columnTemplate = levelSeriesTemplate.columns.template;
      this.addLabel(levelSeriesTemplate);
      this.setupColumn(columnTemplate);
      this.setHoverState(columnTemplate);
      this.registerClickEvent(columnTemplate, hitConsumer);
    }
    return chart;
  }

  private initChart(nodeSelector: string, data: SkillTreeNodeModel[]): am4charts.TreeMap {
    const chart = am4core.create(nodeSelector, am4charts.TreeMap);
    data.forEach(d => d.color = '#b4fff9');
    chart.data = data;
    chart.dataFields.id = 'id';
    chart.dataFields.value = 'value';
    chart.dataFields.name = 'name';
    chart.dataFields.children = 'children';
    chart.dataFields.color = 'color';
    chart.maxLevels = 1;
    chart.navigationBar = new am4charts.NavigationBar();
    return chart;
  }

  private styleContainer(levelSeriesTemplate: TreeMapSeries): void {
    levelSeriesTemplate.strokeWidth = 2;
    levelSeriesTemplate.bulletsContainer.hiddenState.properties.opacity = 1;
    levelSeriesTemplate.bulletsContainer.hiddenState.properties.visible = true;
  }

  private addLabel(levelSeriesTemplate: TreeMapSeries): void {
    const labelBullet = levelSeriesTemplate.bullets.push(new am4charts.LabelBullet());
    labelBullet.locationY = 0.5;
    labelBullet.locationX = 0.5;
    labelBullet.label.text = '{name}';
    labelBullet.label.fill = am4core.color('black');
  }

  private setupColumn(column: Column): void {
    column.column.cornerRadius(5, 5, 5, 5);
    column.tooltipText = '{description}';
  }

  private registerClickEvent(columnTemplate: Column, hitConsumer: (Object) => void): void {
    columnTemplate.events.on(
      'hit',
      ev => {
        const dataContext = ev.target.dataItem.dataContext as SkillTreeNodeModel;
        return hitConsumer({
          id: dataContext.id,
          name: dataContext.name,
          value: dataContext.value,
        });
      }
    );
  }

  private setHoverState(columnTemplate: Column): void {
    const hoverState = columnTemplate.states.create('hover');
    hoverState.adapter.add('fill', (fill) => {
      if (fill instanceof am4core.Color) {
        return am4core.color(am4core.colors.brighten(fill.rgb, -0.2));
      }
      return fill;
    });
  }
}
