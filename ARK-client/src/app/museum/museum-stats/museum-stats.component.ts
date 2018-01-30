import { Component, OnInit } from "@angular/core";
import { MuseumService } from "../../services/museum.service";
import { ActivatedRoute } from "@angular/router";
import { Museum } from "../../models/Museum.model";
import { Position } from "../../models/Position.model";


@Component({
    selector: 'app-museum-stats',
    templateUrl: './museum-stats.component.html',
    styleUrls: ['./museum-stats.component.scss']
})
export class MuseumStatsComponent implements OnInit {

    
    public objectOfWorks;
    public stateOfWorks;
    public museumId: number;
    public authors;
    public museum: Museum;

    constructor(private museumService: MuseumService, private route: ActivatedRoute){}

    ngOnInit(){
        this.route.params.subscribe( params => {
            this.museumId = params['id'];
            this.museumService.getMuseums().subscribe((response) =>{
                
                const museums = response.json();
                museums.forEach(museum => {
                    if (museum['repositoryId'] == params['id'])
                        this.museum = new Museum(museum.repositoryId, museum.repositoryName, museum.city, new Position(museum.latitude, museum.longitude));
                });
            });
        })
        this.getStatisticsData();
    }

    getStatisticsData(){
        this.museumService.getObjectOfWorkStatistics(this.museumId).subscribe((response) => {
            this.objectOfWorks = response.json();
            this.objectOfWorks.sort((a,b) => b.value - a.value);
            
        });

        this.museumService.getStateOfWorkStatistics(this.museumId).subscribe((response) => {
            this.stateOfWorks = response.json().filter(el => el.name != null);
            console.log(this.stateOfWorks);
        });

        this.museumService.getMuseumAuthorsStatistics(this.museumId).subscribe((response) => {
            this.authors = response.json();
            this.authors.sort((a,b) => b.value - a.value);
            this.authors = this.authors.slice(0,50);
            console.log(this.authors);
        })

    }
}