import { Component, Input } from "@angular/core";
import { NgxCarousel } from "ngx-carousel";
import { Router } from "@angular/router";

@Component({
    selector: 'app-artist-list',
    templateUrl: './artist-list.component.html',
    styleUrls: ['./artist-list.component.scss']
})
export class ArtistListComponent {

    public carouselTile: NgxCarousel;
    @Input()
    artists;

    constructor(private router: Router){
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

    onArtistClick(artist){
        this.router.navigate(['/artist', artist.id]);
    }

}