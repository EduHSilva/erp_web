import LoginView from "@/views/LoginView.vue";

const loginRoutes = [
    {
        path: '/login',
        name: 'login',
        component: LoginView,
    },
    {
        path: "/logout",
        name: "logout",
        beforeEnter: (to: any, from: any, next: any) => {
            localStorage.clear();

            next({path: '/login'});
        },
        component: LoginView
    }
]

export default loginRoutes