import * as am4core from '@amcharts/amcharts4/core';
import * as am4charts from '@amcharts/amcharts4/charts';
import am4themes_dark from '@amcharts/amcharts4/themes/dark';
import am4themes_animated from '@amcharts/amcharts4/themes/animated';
import {Injectable} from '@angular/core';
import {SkillTreeNode} from '../skill-tree/skill-tree-node';


@Injectable({
  providedIn: 'root'
})
export class SkillTreeAmchartsChartBuilderService {

  buildChart(nodeSelector: string, data: SkillTreeNode[]): am4charts.TreeMap {
    am4core.useTheme(am4themes_dark);
    am4core.useTheme(am4themes_animated);
    const chart = am4core.create(nodeSelector, am4charts.TreeMap);
    chart.data = data;
    chart.dataFields.value = 'value';
    chart.dataFields.name = 'name';
    chart.dataFields.children = 'children';
    chart.navigationBar = new am4charts.NavigationBar();
    return chart;
  }
}
