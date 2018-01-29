import { Art } from "../models/Art.model";
import { Dimensions } from "../models/Dimensions.model";
import { EventEmitter, Output, Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { APP_CONSTANTS } from "../constants";

@Injectable()
export class ArtService{
    
    constructor(private http: Http){}

    getArtById(id){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artwork/${id}`);
    }

    getArtsForPage(pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?pageNumber=${pageNo}&pageSize=${pageSize}`);
    }

    getArtsByArtist(artist, pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?author=${artist}&pageNumber=${pageNo}&pageSize=${pageSize}`);
    }

    getArtsByMuseum(museumId, pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?repositoryId=${museumId}&pageNumber=${pageNo}&pageSize=${pageSize}`);
    }

    getArtsBySearch(name, author, pageNo, pageSize){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/artworks?pageNumber=${pageNo}&pageSize=${pageSize}&name=${name}&author=${author}`);
    }
}
