import { Component, Input } from "@angular/core";
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";
import { ActivatedRoute } from "@angular/router";
import { OnInit } from "@angular/core/src/metadata/lifecycle_hooks";
import { Dimensions } from "../../models/Dimensions.model";
import { MuseumService } from "../../services/museum.service";


@Component({
    selector: 'app-art-detail',
    templateUrl: './art-detail.component.html',
    styleUrls: ['./art-detail.component.scss']
})
export class ArtDetailComponent implements OnInit{
    art: Art;
    artByArtist: Art[];
    artByMuseum: Art[];
    fullDescription: string;
    authorId: number;
    museumName: string;

    constructor(private museumService: MuseumService, private artService: ArtService, private route: ActivatedRoute){}

    ngOnInit(){
        this.route.params.subscribe(
            params => {
                this.artService.getArtById(params['id']).subscribe((response) => {
                    const art = response.json();
                    this.art =  new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId);
                    this.authorId = art.author.id;
                    this.fullDescription = this.art.description.slice();
                    this.art.description = this.art.description.substring(0, 100) + "...";
                    this.getArtsByArtist();
                    this.getArtsByMuseum();
                    this.getMuseumName();
                });
            }
        );
    }
    getMuseumName(){
        this.museumService.getMuseums().subscribe((response) => {
            const museums = response.json();
            const museum = museums.filter(museum => museum.repositoryId == this.art.museumId)[0];
            this.museumName = museum.repositoryName;
        });
    }

    getArtsByArtist(){
        this.artService.getArtsByArtist(this.art.author, 1, 50).subscribe( (response) =>{
            response = response.json();
            this.artByArtist = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }
    getArtsByMuseum(){
        this.artService.getArtsByMuseum(this.art.museumId, 1, 50).subscribe( (response) =>{
            response = response.json();
            this.artByMuseum = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }

}