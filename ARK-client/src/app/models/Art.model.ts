import { Dimensions } from "./Dimensions.model";


export class Art{
    public id: number;
    public name: string;
    public author: string;
    public year: number;
    public type: string;
    public current: string;
    public description: string;
    public size: string;
    public imagePath: string;
    public state: string

    constructor(id: number, name: string, author: string, year:number, type: string, current: string, description: string, size: string, imagePath: string, state: string){
        this.id = id;
        this.author = author;
        this.name = name;
        this.year = year;
        this.description = description;
        this.current = current;
        this.size = size;
        this.type = type;
        this.imagePath = imagePath;
        this.state = state;
    }
}