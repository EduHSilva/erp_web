import {createRouter, createWebHistory} from 'vue-router'
import UsersView from "@/views/Admin/UsersView.vue";
import HomeView from "@/views/Admin/HomeView.vue";
import AdminView from "@/views/Admin/AdminView.vue";
import ModulesView from "@/views/Admin/ModulesView.vue";
import ProfilesView from "@/views/Admin/ProfilesView.vue";
import ProfileForm from "@/views/Forms/ProfileForm.vue";
import UserForm from "@/views/Forms/UserForm.vue";
import LoginView from "@/views/LoginView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/home',
            name: 'home',
            component: HomeView,
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
        {
            path: '/login',
            name: 'login',
            component: LoginView,
        },
        {
            path: "/logout",
            name: "logout",
            beforeEnter: (to, from, next) => {
                localStorage.clear();

                next({path: '/login'});
            },
            component: LoginView
        }
    ],

    linkExactActiveClass: 'bg-light',

})

router.beforeEach(async (to, from) => {
    if (localStorage.getItem('authToken') == null && to.name !== 'login') {
        return {name: 'login'}
    }
})
export default router
