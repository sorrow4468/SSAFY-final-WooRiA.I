<template>
  <div class="dashboard">

    <VueCurrentWeather 
      apiKey="eb8272a57eb2fdf728d8916fbfc65308"
      lat="35.1795543"
      long="129.0756416"
      @currentWeather="current"
    />
    <dashboard-charts />
    <dashboard-info-block />
  </div>
</template>

<script>
import DashboardCharts from './DashboardCharts.vue'
import DashboardInfoBlock from './DashboardInfoBlock.vue'
import DashboardTabs from './DashboardTabs.vue'
import DashboardMap from './DashboardMap.vue'
import {VueCurrentWeather } from './weather/index'
import { convertTimeZone } from './weather/index'
const logWeatherData = (e) => console.log(e)

const output = (e) => {
  console.log(e[0].timezone_offset)
  console.log(e[0].hourly[0].dt)
  console.log(convertTimeZone(e[0].hourly[0].dt, e[0].timezone_offset))
}
const current = (e) => console.log(e)

export default {
  name: "dashboard",
  components: {
    VueCurrentWeather,
    DashboardCharts,
    DashboardInfoBlock,
  },
  methods: {},
};
</script>

<style lang="scss">
.row-equal .flex {
  .va-card {
    height: 100%;
  }
}

.dashboard {
  .va-card {
    margin-bottom: 0 !important;
    &__title {
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
