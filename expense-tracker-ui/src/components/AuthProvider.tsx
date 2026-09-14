import {createContext, useState} from "react";
import type {AuthContextType} from "../types/AuthContextType.ts";

// export interface AuthTokenContext {
//     accessToken : string,
//     setAccessToken :  React.Dispatch<React.SetStateAction<string>>
// }
//
// const TokenContext = createContext<AuthTokenContext>({
//     accessToken : "",
//     setAccessToken : () => {}
// });

// export function AuthProvider({children} : any) {
//     const [accessToken, setAccessToken] = useState("");
//
//     return (
//         <TokenContext.Provider value={{accessToken, setAccessToken}}>
//             {children}
//         </TokenContext.Provider>
//     )
// }
//
// export default TokenContext;

export const LocalAuthContext = createContext<AuthContextType>({
    accessToken : "",
    isAuthenticated : false,
    login : () => {},
    logout : () => {}
})

export function AuthProvider ({children} : any) {

    const [accessToken, setAccessToken] = useState<string>(() => {
        return localStorage.getItem("accessToken") || "";
    });

    const isAuthenticated = !!accessToken;

    const login = (token : string) => {
        localStorage.setItem("accessToken", token);
        setAccessToken(token);
    };

    const logout = () => {
        localStorage.removeItem("accessToken");
        setAccessToken("");
    }

    return (
        <LocalAuthContext.Provider value={{accessToken, isAuthenticated, login, logout}}>
            {children}
        </LocalAuthContext.Provider>
    )
}