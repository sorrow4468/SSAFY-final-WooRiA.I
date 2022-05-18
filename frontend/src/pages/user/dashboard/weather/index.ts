import { App } from 'vue'
import { utcToTime, utcToDate, convertTimeZone, getWeather } from './utils'
import VueCurrentWeather from './VueCurrentWeather.vue'

/* A way to register the component globally. */
export const OpenWeather = {
  install: (app: App) => {
    app.component('VueCurrentWeather', VueCurrentWeather)
  }
}

export { default as VueCurrentWeather } from './VueCurrentWeather.vue'
export {
  utcToTime,
  utcToDate,
  convertTimeZone,
  getWeather
}