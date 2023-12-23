import {createRouter, createWebHistory} from 'vue-router'
import UsersView from "@/views/UsersView.vue";
import {useUserStore} from "@/store/modules/userModule";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/users',
            name: 'users',
            component: UsersView
        }
    ],
})

export default router
