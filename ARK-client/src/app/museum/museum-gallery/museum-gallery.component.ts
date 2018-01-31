import { Component, OnInit } from "@angular/core";
import { ArtService } from "../../services/art.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Art } from "../../models/Art.model";
import { MuseumService } from "../../services/museum.service";
import { Museum } from "../../models/Museum.model";
import { Position } from "../../models/Position.model";
import { NgxCarousel } from "ngx-carousel/src/ngx-carousel/ngx-carousel.interface";


@Component({
    selector: 'app-museum-gallery',
    templateUrl: './museum-gallery.component.html',
    styleUrls: ['./museum-gallery.component.scss']
})
export class MuseumGalleryComponent implements OnInit{

    public artGallery: Art[];
    public museum: Museum;
    public objectOfWorks;
    public stateOfWorks;
    public museumId: number;
    public authors;
    public authorLength: number;
    public objectLength: number;
    carouselTile: NgxCarousel;
    

    constructor(private museumService: MuseumService,private artService: ArtService, private route: ActivatedRoute, private router: Router){
        
    }

    ngOnInit(){
        this.carouselTile = {
            grid: {xs: 1, sm: 1, md: 1, lg: 1, all: 0},
            slide: 1,
            speed: 600,
            animation: 'lazy',
            point: {
              visible: true
            },
            load: 1,
            touch: true,
            easing: 'ease'
          }
        this.route.params.subscribe(params => {
            this.getArtsByMuseum(params['id']);
            this.museumId = params['id'];
            this.museumService.getMuseums().subscribe((response) =>{
                
                const museums = response.json();
                museums.forEach(museum => {
                    if (museum['repositoryId'] == params['id'])
                        this.museum = new Museum(museum.repositoryId, museum.repositoryName, museum.city, new Position(museum.latitude, museum.longitude));
                });
            });
        });
        this.getStatisticsData();    
    }

    getArtsByMuseum(museumId){
        this.artService.getArtsByMuseum(museumId, 1, 100).subscribe( (response) =>{
            response = response.json();
            this.artGallery = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }

    onArtClick(art){
        this.router.navigate(['/art', art.id]);
    }

   
    capitalizeFirstLetter(string) {
        string = string.toLowerCase();
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    getStatisticsData(){
        this.museumService.getObjectOfWorkStatistics(this.museumId).subscribe((response) => {
            this.objectOfWorks = response.json();
            this.objectOfWorks.sort((a,b) => b.value - a.value);
            this.objectLength = this.objectOfWorks.length * 20 < 800 ? this.objectOfWorks.length * 20 : 800;
            this.objectLength = this.objectLength < 200 ? 200 : this.objectLength;
        });

        this.museumService.getStateOfWorkStatistics(this.museumId).subscribe((response) => {
            this.stateOfWorks = response.json().filter(el => el.name != null);
        });

        this.museumService.getMuseumAuthorsStatistics(this.museumId).subscribe((response) => {
            this.authors = response.json();
            this.authors.sort((a,b) => b.value - a.value);
            this.authors = this.authors.slice(0,50);
            this.authorLength = this.authors.length * 20 < 800 ? this.authors.length * 20 : 800;
            this.authorLength = this.authorLength < 200 ? 200 : this.authorLength;
        })

    }
}