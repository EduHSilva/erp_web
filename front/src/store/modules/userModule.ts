import {defineStore} from "pinia";
import type User from "../types/user"
import axios from "axios";

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
            axios("http://localhost:8080/admin/users").then(e => {
                console.log(e.data)
                this.userList = e.data
            })
        }
    }
})

