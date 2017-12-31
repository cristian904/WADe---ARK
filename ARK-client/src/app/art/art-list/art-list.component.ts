import { Component, OnInit, Input } from "@angular/core";
import { ArtService } from "../../services/art.service";
import { Art } from "../../models/Art.model";
import { Router } from "@angular/router";



@Component({
    selector: 'app-art-list',
    templateUrl: './art-list.component.html',
    styleUrls: ['./art-list.component.scss']

})
export class ArtListComponent implements OnInit{
    @Input()
    arts: Art[];
    
    constructor(private artService: ArtService, private router: Router){}
        
    ngOnInit(){}
    
    onArtClick(art: Art){
        this.artService.artWasSelected.emit(art);
        this.router.navigate(['/art', art.id]);
    }
    

}