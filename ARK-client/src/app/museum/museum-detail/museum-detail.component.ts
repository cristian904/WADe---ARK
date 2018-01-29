import { Component, OnInit } from "@angular/core";
import { Museum } from "../../models/Museum.model";
import { MuseumService } from "../../services/museum.service";
import { ActivatedRoute } from "@angular/router";
import { Position } from "../../models/Position.model";

@Component({
    selector: 'app-museum-detail',
    templateUrl: './museum-detail.component.html',
    styleUrls: ['./museum-detail.component.scss']
})
export class MuseumDetailComponent implements OnInit{

    museum: Museum;

    constructor(private museumService: MuseumService, private route: ActivatedRoute){}

    ngOnInit(){
        this.route.params.subscribe((params) => {
            this.museumService.getMuseums().subscribe((response) =>{
                const museums = response.json();
                museums.forEach(museum => {
                    if (museum['repositoryId'] == params['id'])
                        this.museum = new Museum(museum.repositoryId, museum.repositoryName, museum.city, new Position(museum.latitude, museum.longitude));
                });
            });
        });
    }

}