import { Art } from "../models/Art.model";
import { Dimensions } from "../models/Dimensions.model";
import { EventEmitter, Output } from "@angular/core";

export class ArtService{
    public artWasSelected = new EventEmitter<Art>();

    private arts: Art[] = [
        new Art(0, "Evanghelistul Matei1","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(1, "Evanghelistul Matei2","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(2, "Evanghelistul Matei3","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg"),
        new Art(3, "Evanghelistul Matei4","Nicolae Grigorescu", 1900, "pictura", "modernist", "Pictura modernista", new Dimensions(10, 20), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg/195px-Nicolae_Grigorescu_-_Icoana_Evanghelistului_Matei_de_la_biserica_din_Baicoi.jpg")
    ];

    private previousArts: Art[] = [];

    getArts(){
        return this.arts.slice();
    }

    getSimilarArt(art){
        return this.arts.slice();
    }

    getArtById(id){
        return this.arts.filter(art => art.id == id)[0];
    }

}
