import {toast} from "react-toastify";

export const handleSuccess = (msg : any) => {
    toast.success(msg, {
        position: 'top-right'
    })
}

export const handleError = (msg : any) => {
    toast.error(msg, {
        position: 'top-right'
    })
}