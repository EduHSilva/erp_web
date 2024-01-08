import {createRouter, createWebHistory} from 'vue-router'
import UsersView from "@/views/Admin/UsersView.vue";
import HomeView from "@/views/Admin/HomeView.vue";
import AdminView from "@/views/Admin/AdminView.vue";
import ModulesView from "@/views/Admin/ModulesView.vue";
import ProfilesView from "@/views/Admin/ProfilesView.vue";
import ProfileForm from "@/views/Admin/Forms/ProfileForm.vue";
import UserForm from "@/views/Admin/Forms/UserForm.vue";
import LoginView from "@/views/LoginView.vue";
import SalesView from "@/views/Sales/SalesView.vue";
import ShopView from "@/views/Shop/ShopView.vue";
import loginRoutes from "@/router/modules/loginRoutes";
import adminRoutes from "@/router/modules/adminRoutes";
import salesRoutes from "@/router/modules/salesRoutes";
import shopRoutes from "@/router/modules/shopRoutes";

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

router.beforeEach(async (to) => {
    if (localStorage.getItem('authToken') == null && to.name !== 'login') {
        return {name: 'login'}
    }
})
export default router
