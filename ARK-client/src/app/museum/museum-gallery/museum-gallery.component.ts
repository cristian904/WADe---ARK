import { Component, OnInit } from "@angular/core";
import { ArtService } from "../../services/art.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Art } from "../../models/Art.model";
import { MuseumService } from "../../services/museum.service";
import { Museum } from "../../models/Museum.model";
import { Position } from "../../models/Position.model";


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

    constructor(private museumService: MuseumService,private artService: ArtService, private route: ActivatedRoute, private router: Router){
        
    }

    ngOnInit(){
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

   

}