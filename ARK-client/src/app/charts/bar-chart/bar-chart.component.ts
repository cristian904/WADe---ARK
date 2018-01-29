import { Component, Input } from "@angular/core";



@Component({
    selector: 'app-bar-chart',
    templateUrl: './bar-chart.component.html'
})
export class BarChartComponent{

    @Input()
    single: any[];
    multi: any[];
  
    view: any[] = [1000, 1000];
  
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    @Input()
    xAxisLabel: string;
    showYAxisLabel = true;
    @Input()
    yAxisLabel: string;

    colorScheme = {
      domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
    };
  

    constructor(){}

}