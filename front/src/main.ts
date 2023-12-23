import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import 'bootstrap/scss/bootstrap.scss';
import './assets/main.css'; // Move this line after the Bootstrap import

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.mount('#app');
