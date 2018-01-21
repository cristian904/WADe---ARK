import { Position } from "./Position.model";

export class Museum{
    public id: number;
    public name:string;
    public year: number;
    public location: string;
    public position: Position;
    public type: string;
    public imagePath: string;

    constructor(id:number, name:string, year: number, location:string, position: Position, type: string, imagePath: string){
        this.id = id;
        this.name = name;
        this.year = year;
        this.location = location;
        this.position = position;
        this.type = type;
        this.imagePath = imagePath;
    }

}