export interface Review{
    id?: string;
    isbn: string;
    username: string;
    text: string;
    score: number;
    lastUpdate?: Date;
}