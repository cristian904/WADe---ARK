import { Component } from "@angular/core";
import { ArtistService } from '../services/artist.service';
import { Artist } from "../models/Artist.model";
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from "@angular/forms";

@Component({
    selector: 'app-artist',
    templateUrl: './artist.component.html',
    styleUrls: ['./artist.component.scss']
})
export class ArtistComponent{

    artists: Artist[];
    pageSize = 3;
    p = 1;
    total = 8;
    form: NgForm;
    formSubmited = false;

    public loading = true;

    constructor(private artistService: ArtistService, private route: ActivatedRoute, private router: Router){
          }

    ngOnInit(){
        this.route.queryParams.subscribe((queryParams) => {
            this.loading = false;
            this.p = queryParams['pageNumber'];
            this.pageSize = queryParams['pageSize'];
            if(!this.formSubmited){
                this.getArtistsForPage();
            }else{
                this.getArtistsForSearch(this.form.value.searchName);
            }
        });
        // this.getArtistsForPage();
    }

    onPageChange(event){
        this.router.navigate(['/artists'], {  queryParams: {pageNumber : event, pageSize: this.pageSize} });
    }

    getArtistsForPage(){
        this.artistService.getArtistForPage(this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfAuthors"];
            this.artists = response['authors'].map( artist => new Artist(artist.id, artist.name, "", "", "", artist.image));
        });
    }

    onSubmit(form){
        this.formSubmited = true;
        this.form = form;
        this.p = 1;
        this.getArtistsForSearch(form.value.searchName);
        this.router.navigate(['/artists'], {  queryParams: {pageNumber : this.p, pageSize: this.pageSize} });
    }
    getArtistsForSearch(name){
        this.artistService.getArtistForSearch(name, this.p, this.pageSize).subscribe( (response) =>{
            response = response.json();
            this.total = response["numberOfAuthors"];
            this.artists = response['authors'].map( artist => new Artist(artist.id, artist.name, artist.birthDate, artist.movementName[0], artist.deathDate, artist.image));
            console.log(this.artists);
        });
    }

}