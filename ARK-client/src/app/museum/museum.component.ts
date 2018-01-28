import { Component, OnInit } from "@angular/core";
import { MuseumService } from "../services/museum.service";

import {} from '@agm/core';


@Component({
    selector: 'app-museum',
    templateUrl: './museum.component.html',
    styleUrls: ['./museum.component.scss']
})
export class MuseumComponent implements OnInit{
    private markers = [];

    constructor(private museumService: MuseumService){

    }

    ngOnInit(){
        this.museumService.getMuseums().subscribe((response) =>{
            const museums = response.json();
            this.markers = museums.map((museum) =>{
                return {lat:museum.latitude, long: museum.longitude, name: museum.repositoryName, id: museum.repositoryId};
            });

        });
    }
}