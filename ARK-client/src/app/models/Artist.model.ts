import { Museum } from "./Museum.model";
import { Art } from "./Art.model";


export class Artist{

    public dob: Date;
    public current: string;
    public museums: Museum[];
    public arts: Art[];

}