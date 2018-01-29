import { Position } from "./Position.model";

export class Museum{
    public id: number;
    public name:string;
    public location: string;
    public position: Position;

    constructor(id:number, name:string, location:string, position: Position){
        this.id = id;
        this.name = name;
        this.location = location;
        this.position = position;
    }

}