import {defineConfig} from 'vite'
import path from "path";
import vue from '@vitejs/plugin-vue'
import Unocss from 'unocss/vite'
import {presetUno} from "unocss";
import Layouts from 'vite-plugin-vue-layouts'
import Pages from 'vite-plugin-pages'

/**
 * config ref https://vitejs.dev/config/
 */
export default defineConfig({

    server: {
        port: 21471,
    },

    plugins: [
        Unocss({
            presets: [
                presetUno()
            ],
        }),
        Layouts(),
        Pages(),
        vue(),
    ],
    resolve: {
        alias: {
            '~': `${path.resolve(__dirname, 'src')}/`,
        }
    }

})
