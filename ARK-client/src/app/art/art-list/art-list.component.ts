import { Component, OnInit, Input } from "@angular/core";
import { Art } from "../../models/Art.model";
import { NgxCarousel, NgxCarouselStore } from 'ngx-carousel';
import { ArtService } from "../../services/art.service";
import { Router } from "@angular/router";

@Component({
    selector: 'app-art-list',
    templateUrl: './art-list.component.html',
    styleUrls: ['./art-list.component.scss']

})
export class ArtListComponent implements OnInit{
    @Input()
    arts: Art[];
    public carouselTile: NgxCarousel;

    constructor(private artService: ArtService, private router: Router){}

    ngOnInit(){
        this.carouselTile = {
            grid: {xs: 2, sm: 3, md: 3, lg: 5, all: 0},
            slide: 4,
            speed: 600,
            animation: 'lazy',
            point: {
              visible: true
            },
            load: 2,
            touch: true,
            easing: 'ease'
          }
    }

    onArtClick(art){
        this.artService.artWasSelected.emit(art);
        this.router.navigate(['/art', art.id]);
    }

}