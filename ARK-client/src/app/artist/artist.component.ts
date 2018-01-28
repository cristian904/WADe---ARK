import { Component } from "@angular/core";
import { ArtistService } from '../services/artist.service';
import { Artist } from "../models/Artist.model";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-artist',
    templateUrl: './artist.component.html',
    styleUrls: ['./artist.component.scss']
})
export class ArtistComponent{

    artists: Artist[];
    pageSize = 3;
    p = 1;
    total = 8;

    constructor(private artistService: ArtistService, private route: ActivatedRoute, private router: Router){
          }

    ngOnInit(){
        this.route.queryParams.subscribe((queryParams) => {
            this.p = queryParams['pageNumber'];
            this.pageSize = queryParams['pageSize'];
            this.getArtistsForPage();
        });
        this.getArtistsForPage();
    }
    getArtistsForPage(){
        this.artistService.getArtistForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfAuthors"];
            this.artists = response['authors'].map( artist => new Artist(artist.id, artist.name, artist.birthDate, artist.movementName[0], artist.deathDate, artist.image));
            console.log(response);
        });
    }

}