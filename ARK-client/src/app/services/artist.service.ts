import { Artist } from "../models/Artist.model";
import { Museum } from "../models/Museum.model";
import { Art } from "../models/Art.model";
import { ArtService } from "./art.service";
import { MuseumService } from "./museum.service";



export class ArtistService{

    private artists : Artist[] =[
        new Artist(0, "Nicolae Grigorescu", 1859, "Impresionism", new Array<Museum>(), new Array<Art>(), "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Nicolae_Grigorescu_-_Foto02.jpg/800px-Nicolae_Grigorescu_-_Foto02.jpg"),
        new Artist(1, "Nicoale Tonitza", 1886, "Impresionism", new Array<Museum>(), new Array<Art>(), "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/TonitzaTonitzacca1923.PNG/800px-TonitzaTonitzacca1923.PNG"),
        new Artist(2, "Stefan Luchian", 1868, "Impresionism", new Array<Museum>(), new Array<Art>(), "https://upload.wikimedia.org/wikipedia/commons/2/21/Self-Portrait_Stefan_Luchian.jpg")
    ];

    constructor(){
        const artService = new ArtService();
        this.artists.forEach(artist => {
            artist.arts = artService.getArts();
        });
    }

    getArtists(){
        return this.artists.slice();
    }
    getArtistById(id){
        return this.artists.filter(art => art.id == id)[0];
    }
}