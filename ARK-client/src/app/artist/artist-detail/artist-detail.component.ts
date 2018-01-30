import { Component } from "@angular/core";
import { Artist } from "../../models/Artist.model";
import { ArtistService } from "../../services/artist.service";
import { OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NgxCarousel } from 'ngx-carousel';
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";
import { MuseumService } from "../../services/museum.service";

@Component({
    selector: 'app-artist-detail',
    templateUrl:  './artist-detail.component.html',
    styleUrls: ['./artist-detail.component.scss']
})
export class ArtistDetailComponent implements OnInit{

    artist: Artist;
    artsFromArtists : Art[];
    public museumsStats;
    public objectOfWorkStats;
    public artistsByMovement;


    constructor(private museumService: MuseumService, private artistService: ArtistService, private artService:ArtService, private route: ActivatedRoute){}

    ngOnInit(){
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
                    this.getArtistStatistics(params['id']);
                    this.getAuthorsByMovement(params['id']);
                });
            }
        );
    }


    getArtsByArtist(){
        this.artService.getArtsByArtist(this.artist.name, 1, 30).subscribe( (response) =>{
            response = response.json();
            this.artsFromArtists = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }

    getArtistStatistics(authorId){
        this.artistService.getMuseumByAuthor(authorId).subscribe( (response) => {
            this.museumsStats = response.json().sort((a,b) => b.value - a.value); //.map((m) => {return {value:m.value, name:m.name}});
        });
        this.artistService.getObjectOfWorkByAuthor(authorId).subscribe( (response) => {
            this.objectOfWorkStats = response.json().sort((a,b) => b.value - a.value); //.map((m) => {return {value:m.value, name:m.name}});
        });
    }
    
    getAuthorsByMovement(authorId){
        this.artistService.getMovementsOfArtist(authorId).subscribe( (response) => {
            this.artistsByMovement = response.json();
            this.artistsByMovement = this.artistsByMovement.map((movements) => { return {movementName: movements.movementName.split("@")[0], authors: movements.authors}});
            console.log(this.artistsByMovement);
        });
    }
}
