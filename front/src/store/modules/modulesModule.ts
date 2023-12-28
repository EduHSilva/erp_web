import {defineStore} from "pinia";
import type Module from "../types/module"
import axios from "axios";

interface State {
    modules: Module[],
    totalPages: number,
    page: number,
}

export const useModulesStore = defineStore('module', {
    state: (): State => {
        return {
            modules: [],
            totalPages: 0,
            page: 0
        }
    },
    actions: {
        async getModules(index: number) {
            try {
                let response = await axios(`${import.meta.env.VITE_ADMIN_URL}admin/modules?page=${index}&sort=dateCreated,asc`)
                this.modules = response.data.content
                this.totalPages = parseInt(response.data.totalPages)
                this.page = parseInt(response.data.number)
            } catch (ex) {
                this.showToastError();
            }
        },
        async saveModule(id: String, name: String) {
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
                let toast = document.getElementById("toast-success");
                if (toast !== null) {
                    toast.classList.add("show");
                    setTimeout(function () {
                        if (toast != null) toast.classList.remove("show");
                    }, 3000);
                }
                await this.getModules(0);
            } catch (e) {
                this.showToastError();
            }
        },
        async deleteModule(id: string) {
            try {
                await axios.delete(`${import.meta.env.VITE_ADMIN_URL}admin/module/${id}`)

                let toast = document.getElementById("toast-success");
                if (toast !== null) {
                    toast.classList.add("show");
                    setTimeout(function () {
                        if (toast != null) toast.classList.remove("show");
                    }, 3000);
                }
                await this.getModules(0);
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

