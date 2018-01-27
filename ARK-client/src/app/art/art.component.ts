import { Component, OnInit } from '@angular/core';
import { Art } from '../models/Art.model';
import { ArtService } from '../services/art.service';
import { Dimensions } from '../models/Dimensions.model';

export interface RowData {
    id: number;
    name: string;
    lucky_number: number;
  }

@Component({
    selector: 'app-art',
    templateUrl: './art.component.html',
    styleUrls: ['./art.component.scss'],
    providers: [ArtService]
})
export class ArtComponent implements OnInit{

    arts: Art[];
    pageSize = 8;
    p = 1;
    total = 8;

    constructor(private artService: ArtService){
          }

    ngOnInit(){
        this.arts = this.artService.getArts();
        this.getArtsForPage();
    }

    onPageChange(event){
        this.p = event;
        this.getArtsForPage();

    }

    getArtsForPage(){
        this.artService.getArtsForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfArtworks"];
            this.arts = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state));
            console.log(response);
        });
    }
    
}