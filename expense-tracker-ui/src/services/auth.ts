import {api} from "./api.ts";

interface loginRequestDTO{
    email : string,
    password : string
}

export async function authenticateUser(userDetails : loginRequestDTO) {
    try {
        const response = await api.post('/auth/login', userDetails);

        return response.data;
    } catch (error : any) {
        const errorMessage = error.response?.data?.message || "Login failed";

        throw new Error(errorMessage);
    }
}