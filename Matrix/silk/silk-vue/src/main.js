import {createApp} from 'vue'
import {router} from "./pages/RouteConfig";

import App from './App.vue'

// 引入样式
import 'uno.css'
import 'element-plus/dist/index.css'


createApp(App).use(router).mount('#app')

