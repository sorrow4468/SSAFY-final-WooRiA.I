<template>
  <div class="cctv-border"> 
    <div>
    <canvas class="cctv-border"></canvas>
    </div>
    <div>
    <canvas id="canvas2" class="cctv-border"></canvas>
    </div>
    <div>
    <canvas id="canvas3" class="cctv-border"></canvas>
    </div>
    <div>
    <canvas id="canvas4" class="cctv-border"></canvas>
    </div>
  </div>
</template>

<script>

export default {  
  name: "cctvscreen",
  components: {

  },
  mounted() {
    // 1번
            var client = new WebSocket('wss://k6e2021.p.ssafy.io/api/cctv1/');
            var canvas = document.querySelector('canvas');
            var jsmpeg = require('jsmpeg');
            var player = new jsmpeg(client, {
              canvas: canvas
            });
            console.log(player)
      // 2번
            var client2 = new WebSocket('wss://k6e2021.p.ssafy.io/api/cctv2/');
            // querySelector 에서 id 형식으로 받아올 것
            var canvas2 = document.querySelector('#canvas2'); 
            var jsmpeg = require('jsmpeg');
            var player2 = new jsmpeg(client2, {
              canvas : canvas2
            });
            console.log(player2)
      // 3번
            var client3 = new WebSocket('wss://k6e2021.p.ssafy.io/api/cctv3/');
            // querySelector 에서 id 형식으로 받아올 것
            var canvas3 = document.querySelector('#canvas3'); 
            var jsmpeg = require('jsmpeg');
            var player3 = new jsmpeg(client3, {
              canvas : canvas3
            });
            console.log(player3)
      // 4번
            var client4 = new WebSocket('wss://k6e2021.p.ssafy.io/api/cctv4/');
            // querySelector 에서 id 형식으로 받아올 것
            var canvas4 = document.querySelector('#canvas4'); 
            var jsmpeg = require('jsmpeg');
            var player4 = new jsmpeg(client4, {
              canvas : canvas4
            });
            console.log(player4)
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
