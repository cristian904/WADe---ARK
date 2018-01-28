import { Component, Input } from "@angular/core";
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";
import { ActivatedRoute } from "@angular/router";
import { OnInit } from "@angular/core/src/metadata/lifecycle_hooks";
import { Dimensions } from "../../models/Dimensions.model";


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

    constructor(private artService: ArtService, private route: ActivatedRoute){}

    ngOnInit(){
        this.route.params.subscribe(
            params => {
                this.artService.getArtById(params['id']).subscribe((response) => {
                    const art = response.json();
                    this.art =  new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId);
                    this.fullDescription = this.art.description.slice();
                    this.art.description = this.art.description.substring(0, 100) + "...";
                    this.getArtsByArtist();
                    this.getArtsByMuseum();
                });
            }
        );
    }

    getArtsByArtist(){
        this.artService.getArtsByArtist(this.art.author, 0, 30).subscribe( (response) =>{
            response = response.json();
            this.artByArtist = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }
    getArtsByMuseum(){
        console.log(this.art.museumId);
        this.artService.getArtsByMuseum(this.art.museumId, 0, 30).subscribe( (response) =>{
            response = response.json();
            console.log(response);
            this.artByMuseum = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
            console.log("aaaaaaaaaaaaaaaaa");

        });
    }

}