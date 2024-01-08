import AdminView from "@/views/Admin/AdminView.vue";
import ModulesView from "@/views/Admin/ModulesView.vue";
import ProfilesView from "@/views/Admin/ProfilesView.vue";
import UserForm from "@/views/Admin/Forms/UserForm.vue";
import UsersView from "@/views/Admin/UsersView.vue";
import ProfileForm from "@/views/Admin/Forms/ProfileForm.vue";

const adminRoutes = [
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
    }
]

export default adminRoutes