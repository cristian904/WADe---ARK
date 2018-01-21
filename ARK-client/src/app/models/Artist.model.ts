import { Museum } from "./Museum.model";
import { Art } from "./Art.model";


export class Artist{
    public id: number;
    public name: string;
    public yob: number;
    public current: string;
    public museums: Museum[];
    public arts: Art[];
    public imagePath: string;

    constructor(id: number, name:string, yob: number, current: string, museums: Museum[], arts: Art[], imagePath: string){
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.current = current;
        this.museums = museums;
        this.arts = arts;
        this.imagePath = imagePath;
    }

}