import {defineStore} from "pinia";
import type User from "../types/user"
import axios from "axios";
import .meta.env.__APP_ENV__
interface State {
    userList: User[]
}

export const useUserStore = defineStore('user', {
    state: (): State => {
        return {
            userList: [],
        }
    },
    actions: {
        getUsers() {
            axios(`${import.meta.env.VITE_ADMIN_URL}admin/users`).then(e => {
                console.log(e.data)
                this.userList = e.data
            })
        }
    }
})

