<template>
  <div class="pageSet">
    <div class="flex xs6 md6 mt-3 ml-5">
      <cctvscreen />
    </div>
    <div class="flex xs6 md6 mt-3 mr-5">
      <detailInfo />
    </div>
  </div>
</template>

<script>
import cctvscreen from "../cctvPages/cctvScreen.vue";
import detailInfo from "./detailInfo.vue";

export default {
  name: "cctvbase",
  components: {
    cctvscreen,
    detailInfo
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
  /* margin-top: 5%; */
  display: flex;
  flex-direction: row;
}
</style>
