import { Art } from "../models/Art.model";
import { Dimensions } from "../models/Dimensions.model";
import { EventEmitter, Output, Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { APP_CONSTANTS } from "../constants";

@Injectable()
export class ArtService{
    public artWasSelected = new EventEmitter<Art>();

    private arts: Art[] = [
        new Art(0, "Evanghelistul Matei1","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(1, "Evanghelistul Matei2","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(2, "Evanghelistul Matei3","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(3, "Evanghelistul Matei4","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(4, "Evanghelistul Matei5","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(5, "Evanghelistul Matei6","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(6, "Evanghelistul Matei7","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(7, "Evanghelistul Matei8","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg")
    ];

    constructor(private http: Http){}

    getArts(){
        return this.arts.slice();
    }

    getSimilarArt(art){
        return this.arts.slice();
    }

    getArtById(id){
        this.http.get(APP_CONSTANTS + "/artworks").subscribe(
            (response) => {
                console.log(response.json());
            }
        );
        return this.arts.filter(art => art.id == id)[0];
    }

    getArtsForPage(pageNo, pageSize){

        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?pageNumber=${pageNo}&pageSize=${pageSize}`);
    }


}
