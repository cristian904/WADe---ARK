import { Component, Input } from "@angular/core";
import { Router } from "@angular/router";



@Component({
    selector: 'app-pie-grid-chart',
    templateUrl: './pie-grid-chart.component.html'
})
export class PieGridChartComponent {

    @Input()
    mapping: any[];
    @Input()
    about: string;
    @Input()
    single: any[];
    multi: any[];
    @Input()
    view: any[] = [700, 400];
  
    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Country';
    showYAxisLabel = true;
    yAxisLabel = 'Population';
  
    colorScheme = {
      domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA', 'cyan', 'black', 'red', 'orange']
    };
  
    // line, area
    autoScale = true;

    constructor(private router: Router){}

    onSelect(event){
        console.log(event);
        this.router.navigate([`/${this.about}`, this.mapping.filter(field => field.name == event.name)[0].id]);
    }

}