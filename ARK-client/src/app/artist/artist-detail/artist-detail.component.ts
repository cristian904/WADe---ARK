import { Component } from "@angular/core";
import { Artist } from "../../models/Artist.model";
import { ArtistService } from "../../services/artist.service";
import { OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NgxCarousel } from 'ngx-carousel';
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";

@Component({
    selector: 'app-artist-detail',
    templateUrl:  './artist-detail.component.html',
    styleUrls: ['./artist-detail.component.scss']
})
export class ArtistDetailComponent implements OnInit{

    artist: Artist;
    artsFromArtists : Art[];


    constructor(private artistService: ArtistService, private artService:ArtService, private route: ActivatedRoute){}

    ngOnInit(){
        // this.artist = this.artistService.getArtistById(this.route.snapshot.params['id']);
        this.route.params.subscribe(
            params => {
                this.artistService.getArtistById(params['id']).subscribe((response) => {
                    const artist = response.json();
                    this.artist =  new Artist(artist.id, artist.name, artist.birthDate, "not available", artist.deathDate, artist.image);
                    if(this.artist.birthDate != null){
                        this.artist.birthDate = this.artist.birthDate.split("^^")[0];
                    }
                    if(this.artist.deathDate != null){
                        this.artist.deathDate = this.artist.deathDate.split("^^")[0];
                    }
                    if(this.artist.deathDate != null){
                        this.artist.deathDate = this.artist.deathDate.split("^^")[0];
                    }
                    if(artist.movementName.length>0){
                        console.log(artist.movementName);
                        this.artist.current = artist.movementName.map(m => m.split("@")[0]).reduce((acc, x) => {return acc + ", " + x });
                    }
                    this.getArtsByArtist();
                });
            }
        );

    }
    getArtsByArtist(){
        this.artService.getArtsByArtist(this.artist.name, 0, 30).subscribe( (response) =>{
            response = response.json();
            this.artsFromArtists = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }
}