import {defineStore} from "pinia";
import type Profile from "../types/profile"
import axios from "axios";

interface State {
    profiles: Profile[],
    totalPages: number,
    page: number,
}

export const useProfileStore = defineStore('profile', {
    state: (): State => {
        return {
            profiles: [],
            totalPages: 0,
            page: 0
        }
    },
    actions: {
        async get(index: number) {
            try {
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/profiles?page=${index}&sort=dateCreated,asc`)
                this.profiles = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                this.showToastError();
            }
        },
        async save(id: String, name: String) {
            try {
                if (id.trim() != "") {
                    await axios.put(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${id}`, {
                        name
                    })
                } else {
                    await axios.post(`${import.meta.env.VITE_ADMIN_URL}admin/profiles`, {
                        name,
                        dateCreated: new Date()
                    })
                }
                let toast = document.getElementById("toast-success");
                if (toast !== null) {
                    toast.classList.add("show");
                    setTimeout(function () {
                        if (toast != null) toast.classList.remove("show");
                    }, 3000);
                }
                await this.get(0);
            } catch (e) {
                this.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axios.delete(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${id}`)

                let toast = document.getElementById("toast-success");
                if (toast !== null) {
                    toast.classList.add("show");
                    setTimeout(function () {
                        if (toast != null) toast.classList.remove("show");
                    }, 3000);
                }
                await this.get(0);
            } catch (e) {
                this.showToastError();
            }
        },
        showToastError() {
            let toast = document.getElementById("toast-error");
            if (toast !== null) {
                toast.classList.add("show");
                setTimeout(function () {
                    if (toast != null) toast.classList.remove("show");
                }, 3000);
            }
        }
    }
})

