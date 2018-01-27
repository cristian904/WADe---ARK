import { Component, OnInit } from "@angular/core";
import { Museum } from "../../models/Museum.model";
import { MuseumService } from "../../services/museum.service";

@Component({
    selector: 'app-museum-detail',
    templateUrl: './museum-detail.component.html',
    styleUrls: ['./museum-detail.component.scss']
})
export class MuseumDetailComponent implements OnInit{

    museum: Museum;

    constructor(private museumService: MuseumService){}

    ngOnInit(){
        this.museum = this.museumService.getMuseums()[0];
    }

}