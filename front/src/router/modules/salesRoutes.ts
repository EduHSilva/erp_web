import SalesView from "@/views/Sales/SalesView.vue";
import ClientView from "@/views/Sales/ClientView.vue";

const salesRoutes = [
    {
        path: '/sales',
        name: 'sales',
        component: SalesView,
    },
    {
        path: '/sales/clients',
        name: 'clients',
        component: ClientView,
    },
]


export default salesRoutes