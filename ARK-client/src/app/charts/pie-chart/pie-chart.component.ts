import { Component, Input } from "@angular/core";



@Component({
    selector: 'app-pie-chart',
    templateUrl: './pie-chart.component.html'
})
export class PieChartComponent{

  @Input()
  single: any[];

  multi: any[];
  @Input()
  view: any[] = [600, 500];

  // options
  showLegend = false;

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  // pie
  showLabels = true;
  explodeSlices = false;
  doughnut = false;

  

  constructor() {
  }
}