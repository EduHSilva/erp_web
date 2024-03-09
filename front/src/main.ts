import { numberFormats } from "@/translations/numberFormats";
import 'bootstrap/js/index.esm.js';
import 'bootstrap/scss/bootstrap.scss';
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import { createI18n } from "vue-i18n";
import App from './App.vue';
import './assets/main.css';
import router from './router';
import enUS from './translations/en.json';
import ptBr from './translations/pt.json';

type MessageSchema = typeof enUS


const i18n = createI18n<[MessageSchema], 'EN' | 'PT'>({
    locale: 'PT',
    messages: {
        'EN': enUS,
        'PT': ptBr
    },
    datetimeFormats: {
        'EN': {
            short: {
                year: 'numeric',
                month: 'numeric',
                day: 'numeric'
            },
        }, 
        'PT': {
            short: {
                day: 'numeric',
                month: 'numeric',
                year: 'numeric',
            }
    }
    },
    numberFormats
})

const app = createApp(App);
app.use(createPinia());
app.use(router);
app.use(i18n)

router.beforeEach(async (to) => {
    if (localStorage.getItem('authToken') == null && to.name !== 'login') {
        return {name: 'login'}
    }
    
    switch (localStorage.getItem('language')) {
        case 'EN':
            i18n.global.locale = 'EN'
            break;
        case 'PT':
        default:
            i18n.global.locale = 'PT'
    }
})

app.mount('#app');
