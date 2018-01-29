import { Museum } from "./Museum.model";
import { Art } from "./Art.model";


export class Artist{
    public id: number;
    public name: string;
    public birthDate: string;
    public current: string;
    public deathDate: string;
    public imagePath: string;

    constructor(id: number, name:string, birthDate: string, current: string, deathDate: string, imagePath: string){
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.current = current;
        this.deathDate = deathDate;
        this.imagePath = imagePath;
    }

}