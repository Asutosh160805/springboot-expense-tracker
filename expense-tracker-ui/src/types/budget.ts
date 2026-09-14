import type {Category} from "./expense.ts";

export const months = ["JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"]

export type Month = typeof months[number];

export interface Budget {
    id : number,
    amount : number,
    month : Month,
    year : number
}

export interface BudgetRequest {
    amount : number,
    month : Month,
    year : number
}

export interface BudgetStatus {
    budget : number,
    spent : number,
    remaining : number,
    percentageUsed : number,
    overspent : boolean,
    message : string,
    status : "SAFE" | "WARNING" | "EXCEEDED",
    color : "GREEN" | "YELLOW" | "RED",
}

export interface Report {
    category : Category,
    totalAmount : number,
}

export type MonthlyReport = Report[];