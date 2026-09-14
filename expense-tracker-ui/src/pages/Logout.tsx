import {Navigate} from "react-router-dom";
import {useContext} from "react";
import {LocalAuthContext} from "../components/AuthProvider.tsx";

export function Logout() {
    const {logout} = useContext(LocalAuthContext);
    logout();
    return (
        <Navigate to='/login' replace />
    )
}