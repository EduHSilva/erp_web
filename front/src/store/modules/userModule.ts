import {defineStore} from "pinia";
import type User from "../types/user"
import axios from "axios";
import .meta.env.__APP_ENV__
interface State {
    userList: User[],
    totalPages: 0,
    page: 0
}

export const useUserStore = defineStore('user', {
    state: (): State => {
        return {
            userList: [],
            totalPages: 0,
            page: 0
        }
    },
    actions: {
        get(index: number) {
            axios(`${import.meta.env.VITE_ADMIN_URL}admin/users?page=${index}&sort=dateCreated,asc`).then(e => {
                this.userList = e.data.content
                this.totalPages = e.data.totalPages
                this.page = e.data.number
            })
        }
    }
})

