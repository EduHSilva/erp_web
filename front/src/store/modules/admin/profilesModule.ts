import {defineStore} from "pinia";
import type Profile from "../../types/profile"
import axios from "axios";
import type LinkSidebar from "@/store/types/linkSidebar";
import util from "../../../mixins/util"


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
                util.methods.showToastSuccess(() => window.location.reload());
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${id}`)
                return response.data
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async get(index: number) {
            try {
                let size = ''
                if (index == -1) {
                    size = `size=2147483647`
                }
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/profiles?page=${index}&sort=dateCreated,desc&${size}`)
                this.profiles = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async save(profile: Profile) {
            try {
                console.log("1")
                if (profile.id != undefined && profile.id.trim() != "") {
                    await axios.put(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${profile.id}`, profile)
                } else {
                    await axios.post(`${import.meta.env.VITE_ADMIN_URL}admin/profiles`, profile)
                }

                util.methods.showToastSuccess(() => window.location.href = "/admin/profiles")
            } catch (e) {

                console.log("2")
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axios.delete(`${import.meta.env.VITE_ADMIN_URL}admin/profile/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        },
    }
})

