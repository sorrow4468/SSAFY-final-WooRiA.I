<template>
  <div class="pageSet">
    <div class="flex xs8 md8">
      <calendar />
    </div>
    <div class="flex xs8 md8">
      <boardlist />
    </div>
  </div>
</template>

<script>
import calendar from "./user-calendar.vue";
import boardlist from "./boardlist.vue";
import http from "@/components/common/axios.js";

export default {
  name: "cctvbase",
  components: {
    calendar,
    boardlist
  },

  data() {
    return {};
  },
  computed: {},
  methods: {}
};
</script>
<style>
.pageSet {
  display: flex;
  flex-direction: row;
}
</style>
