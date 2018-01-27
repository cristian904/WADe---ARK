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
    similarArt: Art[];

    constructor(private artService: ArtService, private route: ActivatedRoute){}

    ngOnInit(){
        this.route.params.subscribe(
            params => {
                this.artService.getArtById(params['id']).subscribe((response) => {
                    const art = response.json();
                    console.log(art);
                    this.art =  new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state);
                });
                this.similarArt = this.artService.getSimilarArt(this.art);
            }
        );
       
    }
}