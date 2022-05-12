<template >
  <div class="cctv-border"> 
    <canvas  class="cctv-border"></canvas>
  </div>
</template>

<script>

export default {  
  name: "cctvscreen",
  components: {

  },
  mounted() {
            var client = new WebSocket('wss://k6e2021.p.ssafy.io/api/cctv1/');
            var canvas = document.querySelector('canvas');
            var jsmpeg = require('jsmpeg');
            var player = new jsmpeg(client, {
              canvas: canvas
            });
            console.log(player)
  },
  data() {
    return {
    };
  },
  computed: {
  },
  methods: {
  }
};
</script>
<style scoped>
.cctv-border {
  border: solid 1px black;
  width: 100%;
  height: 100%;
}
</style>