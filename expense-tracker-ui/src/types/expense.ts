export const categories = ["FOOD", "TRAVEL", "SHOPPING", "BILLS", "ENTERTAINMENT", "HEALTH", "OTHER"]

export type Category = typeof categories[number];

export interface Expense {
    id : number,
    title : string,
    amount : number,
    category : Category,
    expenseDate : Date,
    createdAt : Date,
}

export interface ExpenseRequest {
    title : string,
    amount : number,
    category : Category,
}

export interface UpdateExpenseRequest extends ExpenseRequest {}

export interface CreateExpenseRequest extends ExpenseRequest {}

export interface PaginatedExpenseResponse extends ExpenseRequest {
    content : Expense[],
    totalElements : number,
    totalPages : number,
    currentPage : number,
    pageSize : number,
}

export interface GetExpensesRequest {
    page? : number,
    size? : number,
    sortBy? : string,
}

export interface CreateExpenseResponse extends Expense{}