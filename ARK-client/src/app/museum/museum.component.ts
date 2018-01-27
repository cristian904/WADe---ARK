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
        this.markers = [{
            name: "Muzeul Taranului",
            lat: 44.453904,
            long: 26.082689,
            id: 1

        }]

    }

    ngOnInit(){
        this.museumService.getMuseums();
    }

}