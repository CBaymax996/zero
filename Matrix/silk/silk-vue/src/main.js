import {createApp} from 'vue'
import {router} from "./pages/router";

import App from './App.vue'


import 'uno.css'
import 'element-plus/dist/index.css'


createApp(App)
    .use(router)
    .mount('#app')

