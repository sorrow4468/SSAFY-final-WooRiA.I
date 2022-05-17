<template>
  <div class="row row-equal">
    <div class="flex row xl12 xs12">
      <div class="xs3 sm3">
        <div class="flex" v-for="(info, idx) in infoTiles" :key="idx">
          <va-card class="mb-3" :color="info.color">
            <va-card-content>
              <p class="display-1 mb-4" style="color: white">
                {{ info.value }}
              </p>
              <p style="color: white">
                {{ $t("dashboard.info." + info.text) }}
              </p>
            </va-card-content>
          </va-card>
        </div>
      </div>
      <div class="flex xs9 sm9 ml-3"><Table class="haha" /></div>
    </div>
  </div>
</template>

<script>
import Table from "./MarkupTables.vue";

export default {
  name: "DashboardInfoBlock",
  components: {
    Table,
  },
  data() {
    return {
      infoTiles: [
        {
          color: "success",
          value: "803",
          text: "commits",
          icon: "",
        },
        {
          color: "danger",
          value: "57",
          text: "components",
          icon: "",
        },
        {
          color: "info",
          value: "5",
          text: "teamMembers",
          icon: "",
        },
      ],
    };
  },
  methods: {},
  computed: {},
};
</script>

<style lang="scss" scoped>
.haha {
  width: 102.5%;
}
</style>
