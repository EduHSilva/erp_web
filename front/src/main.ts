import {createApp} from 'vue';
import {createPinia} from 'pinia';
import {createI18n} from "vue-i18n";
import App from './App.vue';
import router from './router';
import 'bootstrap/scss/bootstrap.scss';
import 'bootstrap/js/src/modal.js'
import './assets/main.css';
import enUS from './translations/en.json';
import ptBr from './translations/pt.json';

type MessageSchema = typeof enUS

const i18n = createI18n<[MessageSchema], 'en-US' | 'pt-BR'>({
    locale: 'pt-BR',
    messages: {
        'en-US': enUS,
        'pt-BR': ptBr
    }
})

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.use(i18n)

app.mount('#app');
