import { Component, Input } from "@angular/core";
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";
import { Router } from "@angular/router";


@Component({
    selector: 'app-art-detail',
    templateUrl: './art-detail.component.html',
    styleUrls: ['./art-detail.component.scss']
})
export class ArtDetailComponent{
    art: Art;
    similarArt: Art[];

    constructor(private artService: ArtService, private router: Router){}

    ngOnInit(){
        this.artService.artWasSelected.subscribe(
            (art) => {
                this.art = art;
                this.similarArt = this.artService.getSimilarArt(art);
                this.artService.addToPreviousArt(art);
            }
        );
    }

    onBackClick(){
        const art = this.artService.getPreviousArt();
        if(art != null)
        {
            this.art = art;
            this.router.navigate(['/art', art.id]);
        }
    }

}