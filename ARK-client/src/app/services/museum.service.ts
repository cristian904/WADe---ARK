import { Museum } from "../models/Museum.model";
import { Position } from "../models/Position.model";
import { Http, Headers, RequestOptions } from '@angular/http';
import { Injectable } from "@angular/core";
import { APP_CONSTANTS } from "../constants";

@Injectable()
export class MuseumService{

    constructor(private http: Http){}

    getMuseums(){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museums`);
    }

    getObjectOfWorkStatistics(museumId){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museum/${museumId}/group-by/object-of-work`);
    }

    getStateOfWorkStatistics(museumId){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museum/${museumId}/group-by/state`);
    }

    getMuseumAuthorsStatistics(museumId){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museum/${museumId}/group-by/author`);
    }

    getTotalMuseumCount(){
        return this.http.get(`${APP_CONSTANTS.ENDPOINT}/museums/total-count`);
    }
}