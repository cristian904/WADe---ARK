import { Component, OnInit } from '@angular/core';
import { Art } from '../models/Art.model';
import { ArtService } from '../services/art.service';

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
    pageSize = 3;
    p = 1;
    total = 8;

    constructor(private artService: ArtService){
          }

    ngOnInit(){
        this.arts = this.artService.getArts();
        this.getArtsForPage();
    }

    pageChange(event){
        console.log(this.p);
        this.p = event;
        this.getArtsForPage();
        
    }

    getArtsForPage(){
        this.artService.getArtsForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfArtworks"];
            console.log(response);
        }  
        );
    }
    
}