import {defineStore} from "pinia";
import type Profile from "../../types/profile"
import type LinkSidebar from "@/store/types/linkSidebar";
import util from "../../../mixins/util"
import {axiosAdminInstance} from "../../config/axios.config";


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
                await axiosAdminInstance.post(`admin/profile/module`, {
                    idProfile, idModule
                })
                util.methods.showToastSuccess(() => window.location.reload());
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                let response = await axiosAdminInstance(`admin/profile/${id}`)
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
                let response = await axiosAdminInstance(`admin/profiles?page=${index}&sort=dateCreated,desc&${size}`)
                this.profiles = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async save(profile: Profile) {
            try {
                if (profile.id != undefined && profile.id.trim() != "") {
                    await axiosAdminInstance.put(`admin/profile/${profile.id}`, profile)
                } else {
                    await axiosAdminInstance.post(`admin/profiles`, profile)
                }

                util.methods.showToastSuccess(() => window.location.href = "/admin/profiles")
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axiosAdminInstance.delete(`admin/profile/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        },
    }
})

