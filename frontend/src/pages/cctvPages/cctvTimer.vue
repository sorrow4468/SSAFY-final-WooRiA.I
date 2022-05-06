<template id="cctvtimer">
  <div class="container">
    <div class="timer-border">
      <div class="timer-inner">
        <va-button @click="start" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(21, 78, 193); border-color: rgb(21, 78, 193); background: rgba(0, 0, 0, 0);" >Start</va-button>
        <va-button @click="stop" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(228, 34, 34); border-color: rgb(228, 34, 34); background: rgba(0, 0, 0, 0);">Stop</va-button>
        <va-button @click="reset" class="va-button va-button--outline va-button--normal mr-2 mb-2">Reset</va-button>
        <!-- <p>{{formattedElapsedTime}}</p> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "cctvtimer",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
  },
  methods: {
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    stop() {
      clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
    }
  }
};
</script>
<style scoped>
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
  .timer-border {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 700px;
    height: 200px;
  }
  .timer-inner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 600px;
    height: 150px;
    color: red;
  }
</style>