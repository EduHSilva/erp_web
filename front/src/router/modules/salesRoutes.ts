import SalesView from "@/views/Sales/SalesView.vue";
import ClientView from "@/views/Sales/ClientView.vue";
import PersonForm from "@/views/Sales/Forms/PersonForm.vue";
import SellersView from "@/views/Sales/SellersView.vue";
import ProductsView from "@/views/Sales/ProductsView.vue";
import OrdersView from "@/views/Sales/OrdersView.vue";
import ProductForm from "@/views/Sales/Forms/ProductForm.vue";
import OrderForm from "@/views/Sales/Forms/OrderForm.vue";

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
        path: '/sales/persons/sellers',
        name: 'sellers',
        component: SellersView,
    },
    {
        path: '/sales/products',
        name: 'products',
        component: ProductsView,
    },
    {
        path: '/sales/product/:id?',
        name: 'product',
        component: ProductForm,
    },
    {
        path: '/sales/orders',
        name: 'orders',
        component: OrdersView,
    },
    {
        path: '/sales/order/:id?',
        name: 'order',
        component: OrderForm,
    },
    {
        path: '/sales/persons/:id?',
        name: 'persons',
        component: PersonForm,
    },
]


export default salesRoutes