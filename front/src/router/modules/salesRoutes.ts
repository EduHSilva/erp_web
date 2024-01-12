import SalesView from "@/views/Sales/SalesView.vue";
import ClientView from "@/views/Sales/ClientView.vue";
import PersonForm from "@/views/Sales/Forms/PersonForm.vue";

const salesRoutes = [
    {
        path: '/sales',
        name: 'sales',
        component: SalesView,
    },
    {
        path: '/sales/persons/clients',
        name: 'clients',
        component: ClientView,
    },
    {
        path: '/sales/persons/:id?',
        name: 'persons',
        component: PersonForm,
    },
]


export default salesRoutes