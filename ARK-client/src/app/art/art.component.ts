import { Component, OnInit } from '@angular/core';
import { Art } from '../models/Art.model';
import { ArtService } from '../services/art.service';
import { Dimensions } from '../models/Dimensions.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

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
    formSubmited = false;
    form: NgForm;

    constructor(private artService: ArtService, private route: ActivatedRoute, private router: Router){}

    ngOnInit(){
        this.route.queryParams.subscribe((queryParams) => {
            this.p = queryParams['pageNumber'];
            this.pageSize = queryParams['pageSize'];
            if(!this.formSubmited){
                this.getArtsForPage();
            }
            else{
                this.getArtsForSearch(this.form.value.searchName, this.form.value.searchAuthor);
            }
            
        });
        this.getArtsForPage();
    }

    onPageChange(event){
        this.router.navigate(['/arts'], {  queryParams: {pageNumber : event, pageSize: this.pageSize} });
    }

    getArtsForPage(){
        this.artService.getArtsForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfArtworks"];
            this.arts = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }

    onSubmit(form: NgForm){
        this.form = form;
        this.formSubmited = true;
        console.log(form.value.searchName);
        this.p = 1;
        this.getArtsForSearch(form.value.searchName, form.value.searchAuthor);
        this.router.navigate(['/arts'], {  queryParams: {pageNumber : this.p, pageSize: this.pageSize} });
    }

    getArtsForSearch(name, author){
        this.artService.getArtsBySearch(name, author, this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfArtworks"];
            this.arts = response['artworks'].map( art => new Art(art.id, art.title, art.author.name, art.displayYear, art.objectOfWork, "", art.description,art.measurements, art.imageUrl, art.state, art.repositoryId));
        });
    }
    
}