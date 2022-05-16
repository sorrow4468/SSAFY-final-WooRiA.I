<template>
  <va-date-picker
    class="date"
    mode="single"
    v-model="value"
    @click="getcctvlist"
    stateful
    highlight-weekend
  />
</template>

<script>
import http from "@/components/common/axios.js";
import { mapMutations } from "vuex";

export default {
  name: "calendar",
  components: {},
  props: {},
  data() {
    return {
      value: new Date(),
    };
  },

  methods: {
    getcctvlist() {
      console.log(this.value);
      http
        .post("/cctv/find/list", {
          dateTime: this.value,
        })
        .then((res) => {
          console.log(res.data);
          this.$store.state.cctvList = res.data.cctvList;
          console.log(this.$store.state.cctvList);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss">
@import "./vueDatePick.scss";
.date {
  font-size: 1em;

  padding: 2em;
  background: #fff;
  box-shadow: 0 0.2em 1.5em rgba(0, 0, 0, 0.06);
  border-radius: 0.5em;
  border: 1px solid rgba(0, 0, 0, 0.15);
  margin-top: 3em;
  margin-left: 2em;
  height: 485px;
  width: 560px;
}
</style>
