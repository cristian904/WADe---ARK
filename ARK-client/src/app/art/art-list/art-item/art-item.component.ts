import { Component, Input } from "@angular/core";
import { Art } from "../../../models/Art.model";
import { ArtService } from "../../../services/art.service";
import { Router } from "@angular/router";

@Component({
    selector: 'app-art-item',
    templateUrl: './art-item.component.html',
    styleUrls: ['./art-item.component.scss']
})
export class ArtItemComponent{

    @Input()
    art: Art;

    constructor(private artService: ArtService, private router: Router){}

    onArtClick(){
        this.artService.artWasSelected.emit(this.art);
        this.router.navigate(['/art', this.art.id]);
    }

}