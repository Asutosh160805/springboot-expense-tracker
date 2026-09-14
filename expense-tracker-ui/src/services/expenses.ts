import type {
    Category,
    CreateExpenseRequest,
    CreateExpenseResponse, Expense,
    GetExpensesRequest,
    PaginatedExpenseResponse, UpdateExpenseRequest
} from "../types/expense.ts";
import {api} from "./api.ts";

export async function createExpense(expense : CreateExpenseRequest) : Promise<CreateExpenseResponse> {
    try {
        const response = await api.post("/expenses", expense);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getExpenses(expensesRequest : GetExpensesRequest) : Promise<PaginatedExpenseResponse> {
    try {
        const response = await api.get('/expenses', {
            params : expensesRequest
        });
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getExpenseById(expenseId : number) : Promise<Expense> {
    try {
        const response = await api.get(`/expenses/${expenseId}`);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function updateExpense(expenseRequest : UpdateExpenseRequest, expenseId : number) : Promise<Expense> {
    try {
        const response = await api.put(`/expenses/${expenseId}`, expenseRequest);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function deleteExpenseById(expenseId : number) : Promise<Expense> {
    try {
        const response = await api.delete(`/expenses/${expenseId}`);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getExpensesByCategory(category : Category) : Promise<Expense[]> {
    try {
        const response = await api.get(`/expenses/category/${category}`);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getExpensesByMinimumAmount(amount : number) : Promise<[Expense]> {
    try {
        const response = await api.get(`/expenses/amount/${amount}`);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}