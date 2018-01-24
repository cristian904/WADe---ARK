import { Component } from "@angular/core";



@Component({
    selector: 'app-pie-chart',
    templateUrl: './pie-chart.component.html'
})
export class PieChartComponent{

    // Pie
  public pieChartLabels:string[] = ['Curent1', 'Curent2', 'Curent3'];
  public pieChartData:number[] = [300, 500, 100];
  public pieChartType:string = 'pie';
   public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'yellow',
      borderColor: 'rgba(148,159,177,1)'
     
    },
    { // dark grey
      backgroundColor: 'green',
      borderColor: 'rgba(77,83,96,1)'
     
    },
    { // grey
      backgroundColor: 'blue',
      borderColor: 'rgba(148,159,177,1)'
     
    }
  ];
 
  // events
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }
}