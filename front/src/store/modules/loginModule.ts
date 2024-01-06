import {defineStore} from "pinia";
import axios from "axios";
import util from "../../mixins/util"

interface State {
}

export const useLoginStore = defineStore('login', {
    state: (): State => {
        return {}
    },
    actions: {
        async login(credencials: Object) {
            try {
                let response = await axios.post(`${import.meta.env.VITE_ADMIN_URL}auth/login`, credencials)
                return response.data
            } catch (ex) {
                util.methods.showToastError();
            }
        }
    }
})

