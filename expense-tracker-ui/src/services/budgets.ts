import type {Budget, BudgetRequest, BudgetStatus, MonthlyReport} from "../types/budget.ts";
import {api} from "./api.ts";

export async function createBudget(budgetRequest : BudgetRequest) : Promise<Budget> {
    try {
        const response = await api.post("/budgets", budgetRequest);
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getCurrentBudget() : Promise<Budget> {
    try {
        const response = await api.get("/budgets/current");
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getBudgetStatus() : Promise<BudgetStatus> {
    try {
        const response = await api.get("/budgets/status");
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}

export async function getMonthlyReports() : Promise<MonthlyReport> {
    try {
        const response = await api.get("/budgets/reports/monthly");
        return response.data;
    } catch (error : any) {
        throw new Error(error.response?.data?.message || "Something went wrong");
    }
}