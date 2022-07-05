import { User } from "./user";

export interface Rent{
    id?:number;
    isbn:string;
    fromDate?:Date
    toDate:Date;
    returnDate?:Date;
    user:User;
    
}