import { Artist } from "../models/Artist.model";
import { Museum } from "../models/Museum.model";
import { Art } from "../models/Art.model";
import { ArtService } from "./art.service";
import { MuseumService } from "./museum.service";
import { Http } from "@angular/http";
import { Injectable } from "@angular/core";
import { APP_CONSTANTS } from "../constants";


@Injectable()
export class ArtistService{

    constructor(private http: Http){}

    getArtistById(id){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/author/${id}`);
    }

    getArtistForPage(pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/authors?pageNumber=${pageNo}&pageSize=${pageSize}`);
    }

    getArtistForSearch(name, pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/authors?pageNumber=${pageNo}&pageSize=${pageSize}&name=${name}`);
    }
}