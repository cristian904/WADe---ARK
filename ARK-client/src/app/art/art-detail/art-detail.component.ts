import { Component, Input } from "@angular/core";
import { Art } from "../../models/Art.model";
import { ArtService } from "../../services/art.service";
import { ActivatedRoute } from "@angular/router";


@Component({
    selector: 'app-art-detail',
    templateUrl: './art-detail.component.html',
    styleUrls: ['./art-detail.component.scss']
})
export class ArtDetailComponent{
    art: Art;
    similarArt: Art[];

    constructor(private artService: ArtService, private route: ActivatedRoute){}

    ngOnInit(){
        this.art = this.artService.getArtById(this.route.snapshot.params['id']);
    }

    onBackClick(){
        // const art = this.artService.getPreviousArt();
        // if(art != null)
        // {
        //     this.art = art;
        //     this.router.navigate(['/art', art.id]);
        // }
    }

}