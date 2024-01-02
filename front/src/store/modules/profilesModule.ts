import {defineStore} from "pinia";
import type Profile from "../types/profile"
import axios from "axios";
import type LinkSidebar from "@/store/types/linkSidebar";

interface State {
    profiles: Profile[],
    totalPages: number,
    page: number,
    links: LinkSidebar[]
}

export const useProfileStore = defineStore('profile', {
    state: (): State => {
        return {
            profiles: [],
            totalPages: 0,
            page: 0,
            links: [
                {
                    img: "/icons/list.svg",
                    description: "list",
                    link: "/admin/profiles"
                },
                {
                    img: "/icons/add.svg",
                    description: "add",
                    link: "/admin/profile"
                }
            ],
        }
    },
    actions: {
        async addModule(idProfile: string, idModule: string) {
            try {
                await axios.post(`${import.meta.env.VITE_ADMIN_URL}admin/profile/module`, {
                    idProfile, idModule
                })
                window.location.reload()
            } catch (ex) {
                this.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${id}`)
                return response.data
            } catch (ex) {
                this.showToastError();
            }
        },
        async get(index: number) {
            try {
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/profiles?page=${index}&sort=dateCreated,desc`)
                this.profiles = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                this.showToastError();
            }
        },
        async save(profile: Profile) {
            try {
                if (profile.id != undefined && profile.id.trim() != "") {
                    await axios.put(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${profile.id}`, profile)
                } else {
                    await axios.post(`${import.meta.env.VITE_ADMIN_URL}admin/profiles`, profile)
                }
                let toast = document.getElementById("toast-success");
                if (toast !== null) {
                    toast.classList.add("show");
                    setTimeout(function () {
                        if (toast != null) toast.classList.remove("show");
                    }, 3000);
                }
                window.location.href = "/admin/profiles"
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

