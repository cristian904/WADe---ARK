import { Component } from "@angular/core";
import { Artist } from "../../models/Artist.model";
import { ArtistService } from "../../services/artist.service";
import { OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NgxCarousel } from 'ngx-carousel';

@Component({
    selector: 'app-artist-detail',
    templateUrl:  './artist-detail.component.html',
    styleUrls: ['./artist-detail.component.scss']
})
export class ArtistDetailComponent implements OnInit{


    
    artist: Artist;

    constructor(private artistService: ArtistService, private route: ActivatedRoute){}

    ngOnInit(){
        this.artist = this.artistService.getArtistById(this.route.snapshot.params['id']);

    }
}