import { Art } from "../models/Art.model";
import { Dimensions } from "../models/Dimensions.model";
import { EventEmitter, Output, Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { APP_CONSTANTS } from "../constants";

@Injectable()
export class ArtService{
    public artWasSelected = new EventEmitter<Art>();

    private arts: Art[] = [
        new Art(0, "Evanghelistul Matei1","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista","10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(1, "Evanghelistul Matei2","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista","10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(2, "Evanghelistul Matei3","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", "10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(3, "Evanghelistul Matei4","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", "10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(4, "Evanghelistul Matei5","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista","10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(5, "Evanghelistul Matei6","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", "10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(6, "Evanghelistul Matei7","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", "10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna"),
        new Art(7, "Evanghelistul Matei8","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", "10 X 20", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg", "buna")
    ];

    constructor(private http: Http){}

    getArts(){
        return this.arts.slice();
    }

    getSimilarArt(art){
        return this.arts.slice();
    }

    getArtById(id){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artwork/${id}`);
    }

    getArtsForPage(pageNo, pageSize){

        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?pageNumber=${pageNo}&pageSize=${pageSize}`);
    }


}
