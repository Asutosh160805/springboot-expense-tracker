import {useContext} from "react";
import {LocalAuthContext} from "./AuthProvider.tsx";
import {Navigate, Outlet} from "react-router-dom";

export function ProtectedRoute() {
    const {isAuthenticated} = useContext(LocalAuthContext);
    if (!isAuthenticated) {
        return <Navigate to="/login" replace />
    }
    return (
        <Outlet />
    )
}