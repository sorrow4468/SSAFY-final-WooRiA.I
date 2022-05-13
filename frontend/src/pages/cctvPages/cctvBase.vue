<template>
  <div  class="pageSet">
    <div class="flex xs8 md8">
      <cctvscreen/>

    </div>
    <div class="flex xs4 md4">
      <cctvclock color="green"/>
      <cctvtimer/>
    </div>
  </div>
</template>

<script>
import cctvclock from './cctvClock.vue'
import cctvtimer from './cctvTimer.vue'
import cctvscreen from './cctvScreen.vue'

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
  }
};
</script>
<style>
.pageSet {
  display: flex;
  flex-direction: row;
}
</style>