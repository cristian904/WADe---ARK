import { Component, OnInit } from '@angular/core';
import { Art } from '../models/Art.model';
import { ArtService } from '../services/art.service';
import { Dimensions } from '../models/Dimensions.model';
import { ActivatedRoute, Router } from '@angular/router';

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

    constructor(private artService: ArtService, private route: ActivatedRoute, private router: Router){}

    ngOnInit(){
        this.route.queryParams.subscribe((queryParams) => {
            this.p = queryParams['pageNumber'];
            this.pageSize = queryParams['pageSize'];
            this.getArtsForPage();
        });
        this.getArtsForPage();
    }

    onPageChange(event){
        this.router.navigate(['/art'], {  queryParams: {pageNumber : event, pageSize: this.pageSize} });
    }

    getArtsForPage(){
        this.artService.getArtsForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfArtworks"];
            this.arts = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, 1900, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
            console.log(response);
        });
    }
    
}