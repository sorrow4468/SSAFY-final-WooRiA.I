<template>
  <div class="pageSet">
    <div class="flex xs6 md6 mt-5 ml-5">
      <br />
      <br />
      <br />
      <br />
      <Xgplayer :config="this.$store.state.config" @player="Player = $event" />
    </div>

    <div class="flex xs6 md6 mt-3 mr-5">
      <detailInfo />
    </div>
  </div>
</template>

<script>
import detailInfo from "./tableDetailInfo.vue";
import Xgplayer from "xgplayer-vue";

export default {
  name: "tableDetail",
  components: {
    detailInfo,
    Xgplayer,
  },

  data() {
    return {
      config: {
        id: "vs",
        // url: 'http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4'
        // url: 'https://ssafit-01-bucket.s3.ap-northeast-2.amazonaws.com/02_17-17-39.avi'
        url: "",
      },
      Player: null,
    };
  },
  computed: {},
  methods: {},
  mounted() {
    this.config.url = this.$store.state.detailList.video_URL;
    console.log(this.config.url);
  },
};
</script>
<style>
.pageSet {
  /* margin-top: 5%; */
  display: flex;
  flex-direction: row;
}
</style>
