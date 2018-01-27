import { Component } from "@angular/core";



@Component({
    selector: 'app-pie-chart',
    templateUrl: './pie-chart.component.html'
})
export class PieChartComponent{

  single = [
    {
      "name": "Germany",
      "value": 8940000
    },
    {
      "name": "USA",
      "value": 5000000
    },
    {
      "name": "France",
      "value": 7200000
    }
  ];

  multi: any[];

  view: any[] = [700, 400];

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
  
  onSelect(event) {
    console.log(event);
  }
}