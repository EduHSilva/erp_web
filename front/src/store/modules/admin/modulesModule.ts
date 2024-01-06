import {defineStore} from "pinia";
import type Module from "../../types/module"
import axios from "axios";
import type LinkSidebar from "@/store/types/linkSidebar";
import util from "../../../mixins/util"

interface State {
    modules: Module[],
    totalPages: number,
    page: number,
    links: LinkSidebar[]
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
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/modules?page=${index}&sort=dateCreated,asc&${size}`)
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
                    await axios.put(`${import.meta.env.VITE_ADMIN_URL}admin/module/${id}`, {
                        name
                    })
                } else {
                    await axios.post(`${import.meta.env.VITE_ADMIN_URL}admin/modules`, {
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
                await axios.delete(`${import.meta.env.VITE_ADMIN_URL}admin/module/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        }
    }
})

