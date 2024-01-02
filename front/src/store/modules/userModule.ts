import {defineStore} from "pinia";
import type User from "../types/user"
import axios from "axios";
import type LinkSidebar from "@/store/types/linkSidebar";
import .meta.env.__APP_ENV__
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
                    link: "/admin/users/edit"
                }
            ]
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

