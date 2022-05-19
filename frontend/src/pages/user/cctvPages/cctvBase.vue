<template>
  <div class="">
    <div class="flex xs12 md12 " style="height:100px;">
      <cctvtimer/>
    </div>
    <div class="flex xs12 md12 justify--center">
      <cctvscreen/>
    </div>
  </div>
</template>

<script>
import cctvclock from './cctvClock.vue'
import cctvtimer from './cctvTimer.vue'
import cctvscreen from './cctvScreen.vue'
import axios from 'axios'


export default {
  name: "cctvbase",
  components: {
    cctvclock,
    cctvtimer,
    cctvscreen

  },

  data() {
    return {
    };
  },
  computed: {
  },
  methods: {
    refresh() {
      axios.get(
        'https://k6e2021.p.ssafy.io/api/streaming/cctv1/start'

      ).then(

        'https://k6e2021.p.ssafy.io/api/streaming/cctv2/start'

      ).then(
        'https://k6e2021.p.ssafy.io/api/streaming/cctv3/start'
      ).then(
       'https://k6e2021.p.ssafy.io/api/streaming/cctv4/start'

      ).catch((err) => {
        console.log(err)}
      )
    }
  }
};
</script>
<style >
.pageSet {
  display: flex;
  flex-direction: column;
}
.pageSet {
  display: flex;
  flex-direction: column;
}
  .timer-mg {
    margin: 0px 10px;
  }
  .refresh {
    padding : 4rem 0rem;

    width: 150px;
    height: 10px;

  }
    .container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: auto;
    flex-direction: column;
  }
  .hour, .min, .secs {
    font-size: 4em;
  }
  strong {
    color: blue;
  }
  p {
    font-family: 'Lucida Sans', sans-serif;
    font-size: 20px;
  }

  .icon-size {
    size: 10rem;
  }
</style>
