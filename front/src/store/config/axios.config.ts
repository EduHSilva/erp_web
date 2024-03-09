import axios from "axios";

const token = localStorage.getItem("authToken")

const axiosAdminInstance = axios.create({
    baseURL: `${import.meta.env.VITE_ADMIN_URL}`,
    headers: {
        Authorization: `Bearer ${token}`
    }
});

const axiosSalesInstance = axios.create({
    baseURL: `${import.meta.env.VITE_SALES_URL}`,
    headers: {
        Authorization: `Bearer ${token}`
    }
});

export { axiosAdminInstance, axiosSalesInstance };

