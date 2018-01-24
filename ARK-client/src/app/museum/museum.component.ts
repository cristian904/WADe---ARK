import { Component, OnInit } from "@angular/core";
import { MuseumService } from "../services/museum.service";


@Component({
    selector: 'app-museum',
    templateUrl: './museum.component.html',
    styleUrls: ['./museum.component.scss']
})
export class MuseumComponent implements OnInit{

    constructor(private museumService: MuseumService){}

    ngOnInit(){
        this.museumService.getMuseums();
    }

}