export interface AuthContextType {
    accessToken : string,
    isAuthenticated : boolean,
    login : (token : string) => void,
    logout : () => void
}