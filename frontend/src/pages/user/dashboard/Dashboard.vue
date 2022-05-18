<template>
  <div class="dashboard">


    <dashboard-charts />
    <dashboard-info-block />
  </div>
</template>

<script>
import DashboardCharts from './DashboardCharts.vue'
import DashboardInfoBlock from './DashboardInfoBlock.vue'


export default {
  name: "dashboard",
  components: {
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
