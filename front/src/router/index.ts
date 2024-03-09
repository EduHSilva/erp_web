import adminRoutes from "@/router/modules/adminRoutes";
import loginRoutes from "@/router/modules/loginRoutes";
import salesRoutes from "@/router/modules/salesRoutes";
import shopRoutes from "@/router/modules/shopRoutes";
import HomeView from "@/views/Admin/HomeView.vue";
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/home',
            name: 'home',
            component: HomeView,
        },
        ...adminRoutes,
        ...salesRoutes,
        ...shopRoutes,
        ...loginRoutes
    ],
    linkExactActiveClass: 'bg-light',
})

export default router
