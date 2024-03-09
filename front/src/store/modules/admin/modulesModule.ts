import type LinkSidebar from "@/store/types/linkSidebar";
import { defineStore } from "pinia";
import util from "../../../mixins/util";
import { axiosAdminInstance } from "../../config/axios.config";
import type Module from "../../types/module";

interface State {
    modules: Module[],
    totalPages: number,
    page: number,
    links: LinkSidebar[],
}


export const useModulesStore = defineStore('module', {
    state: (): State => {
        return {
            modules: [],
            totalPages: 0,
            page: 0,
            links: [
                {
                    img: "/icons/list.svg",
                    description: "list",
                    link: "/admin/modules"
                },
            ],
        }
    },
    actions: {
        async get(index: number) {
            let size = ''
            if (index == -1) {
                size = `size=2147483647`
            }
            try {
                const response = await axiosAdminInstance.get(`admin/modules?page=${index}&sort=dateCreated,asc&${size}`)
                this.modules = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                util.methods.showToastError();
            }
        },

        async save(id: String, name: String) {
            try {
                if (id.trim() != "") {
                    await axiosAdminInstance.put(`admin/module/${id}`, {
                        name
                    })
                } else {
                    await axiosAdminInstance.post(`admin/modules`, {
                        name,
                        dateCreated: new Date()
                    })
                }
                util.methods.showToastSuccess(() => window.location.reload());
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axiosAdminInstance.delete(`admin/module/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        }
    }
})

