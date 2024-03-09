import type LinkSidebar from "@/store/types/linkSidebar";
import { defineStore } from "pinia";
import util from "../../../mixins/util";
import { axiosAdminInstance } from "../../config/axios.config";
import type User from "../../types/user";

interface State {
    userList: User[],
    totalPages: 0,
    page: 0,
    links: LinkSidebar[]
}

export const useUserStore = defineStore('user', {
    state: (): State => {
        return {
            userList: [],
            totalPages: 0,
            page: 0,
            links: [
                {
                    img: "/icons/list.svg",
                    description: "list",
                    link: "/admin/users"
                },
                {
                    img: "/icons/add.svg",
                    description: "add",
                    link: "/admin/user"
                }
            ]
        }
    },
    actions: {
        async get(index: number) {
            try {
                const response = await axiosAdminInstance(`admin/users?page=${index}&sort=dateCreated,asc`)
                this.userList = response.data.content
                this.totalPages = response.data.totalPages
                this.page = response.data.number
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axiosAdminInstance.delete(`admin/user/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                const response = await axiosAdminInstance(`admin/user/${id}`)
                return response.data
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async save(user: User) {
            try {
                if (user.id != undefined && user.id.trim() != "") {
                    await axiosAdminInstance.put(`admin/user/${user.id}`, user)
                } else {
                    await axiosAdminInstance.post(`admin/users`, user)
                }
                util.methods.showToastSuccess(() => window.location.href = "/admin/users");
            } catch (e) {
                util.methods.showToastError();
            }
        },

    },

})

