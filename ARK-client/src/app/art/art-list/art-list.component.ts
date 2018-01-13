import { Component, OnInit, Input } from "@angular/core";
import { Art } from "../../models/Art.model";


@Component({
    selector: 'app-art-list',
    templateUrl: './art-list.component.html',
    styleUrls: ['./art-list.component.scss']

})
export class ArtListComponent{
    @Input()
    arts: Art[];
    

}