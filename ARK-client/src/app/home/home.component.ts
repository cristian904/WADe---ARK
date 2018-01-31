import { Component, OnInit } from "@angular/core";
import { ArtistService } from "../services/artist.service";
import { MuseumService } from "../services/museum.service";
import { ArtService } from "../services/art.service";



@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{

    public artCount:number;
    public artistCount:number;
    public museumCount:number;

    constructor(private artistService: ArtistService, private museumService: MuseumService, private artService: ArtService){}

    ngOnInit(){
        this.artistService.getTotalArtistCount().subscribe((response) =>{
            this.artistCount = response.json();
        });
        this.artService.getTotalArtCount().subscribe((response) =>{
            this.artCount = response.json();
        });
        this.museumService.getTotalMuseumCount().subscribe((response) =>{
            this.museumCount = response.json();
        });
    }
    
}