import { Component } from "@angular/core";
import { ArtistService } from '../services/artist.service';
import { Artist } from "../models/Artist.model";

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

    constructor(private artistService: ArtistService){
          }

    ngOnInit(){
        this.artists = this.artistService.getArtists();
    }

}