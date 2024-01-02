import {createRouter, createWebHistory} from 'vue-router'
import UsersView from "@/views/UsersView.vue";
import HomeView from "@/views/HomeView.vue";
import AdminView from "@/views/AdminView.vue";
import ModulesView from "@/views/ModulesView.vue";
import ProfilesView from "@/views/ProfilesView.vue";
import ProfileForm from "@/views/Forms/ProfileForm.vue";
import UserForm from "@/views/Forms/UserForm.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/admin',
            name: 'admin',
            component: AdminView
        },
        {
            path: '/admin/modules',
            name: 'modules',
            component: ModulesView,
        },
        {
            path: '/admin/profiles',
            name: 'profiles',
            component: ProfilesView,
        },
        {
            path: '/admin/user/:id?',
            name: 'userAddEdit',
            component: UserForm,
        },
        {
            path: '/admin/users',
            name: 'users',
            component: UsersView
        },
        {
            path: '/admin/profile/:id?',
            name: 'profileAddEdit',
            component: ProfileForm,
        },
    ],
    linkExactActiveClass: 'bg-light'
})

export default router
