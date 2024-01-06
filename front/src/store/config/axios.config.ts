import axios from "axios";

let token = localStorage.getItem("authToken")

const instance = axios.create({
    baseURL: `${import.meta.env.VITE_ADMIN_URL}`,
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export {instance};
