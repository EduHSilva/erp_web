import {defineStore} from "pinia";
import type Module from "../types/module"
import axios from "axios";

interface State {
    modules: Module[]
}

export const useModulesStore = defineStore('module', {
    state: (): State => {
        return {
            modules: [],
        }
    },
    actions: {
        getModules() {
            axios(`${import.meta.env.VITE_ADMIN_URL}admin/modules`).then(e => {
                this.modules = e.data
            })
        }
    }
})

