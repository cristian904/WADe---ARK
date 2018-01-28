import { Museum } from "../models/Museum.model";
import { Position } from "../models/Position.model";
import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable } from "@angular/core";
import { APP_CONSTANTS } from "../constants";

@Injectable()
export class MuseumService{

    museums: Museum[] = [
        new Museum(0, "Muzeul Național de Artă al României", 1948, "Bucuresti", new Position(45.23, 60.34), "arta", "https://upload.wikimedia.org/wikipedia/commons/4/4e/Muzeul_National_de_Arta%2C_Bucuresti.jpg"),
        new Museum(1, "Muzeul de arta Cluj-Napoca", 1951, "Cluj-Napoca", new Position(45, 45), "arta", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Palatul_BanffyCJ_%282%29.JPG/420px-Palatul_BanffyCJ_%282%29.JPG"),
        new Museum(2, "Palatul Culturii", 1860, "Iasi", new Position(45, 45), "arta", "https://upload.wikimedia.org/wikipedia/commons/4/42/Palatul_Culturii_Ia%C8%99i.jpg")
    ];

    constructor(private http: Http){}

    getMuseums(){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museums`);
    }
}