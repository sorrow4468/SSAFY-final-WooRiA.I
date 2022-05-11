<template>
  <router-view/>
</template>
<script>
import Vue from 'vue';
// import VueAlertify from 'vue-alertify';
// import Notifications from 'vue-notification';


// Vue.use(VueAlertify);
// Vue.use(Notifications)

    export default {
        name:'app',
        data() {
            return {
                shopId:'',
                accessVerify : ''
            }
        },
        mounted() {// page creation lifecycle function
              this.initWebSocket()
        },
        destroyed: function () {// leave page life cycle function
              this.websocketclose();
        },
        methods: {
            collapse: function(){
                this.isCollapse = !this.isCollapse;
                if (this.isCollapse) {
                    this.iconClass = "cebianlanzhankai";
                } else{
                    this.iconClass = "cebianlanshouhui";
                }
            },
            initWebSocket: function () {
                // WebSocket is different from ordinary requests in terms of protocol, WS is equivalent to http, WSS is equivalent to HTTPS
                this.websock = new WebSocket("wss://xn--vk1bw3clxiimaf76b.kr/api_be/websocket/DPS007");
                this.websock.onopen = this.websocketonopen;
                this.websock.onerror = this.websocketonerror;
                this.websock.onmessage = this.websocketonmessage;
                this.websock.onclose = this.websocketclose;
              },  
              websocketonopen: function () {
                console. log ("WebSocket Connection Successful");
                this.accessVerify = window.localStorage.getItem('accessToken')
              },
              websocketonerror: function () {
                console. log ("WebSocket connection error");
              },
              websocketonmessage: function (e) {
                this.accessVerify = window.localStorage.getItem('accessToken')

                if (this.accessVerify) {
                // 로그인 조건문 달아주기
                alert('위험감지!')
                // this.$alertify.prompt(
                //   '위험 감지!',
                //   e.data,
                //   (evt, value) => this.$alertify.success('ok: ' + value),
                //   () => this.$alertify.error('cancel')
                // );
              }else{
                console.log('')
              }
              },
              websocketclose: function (e) {
                console.log("connection closed (" + e.code + ")");
              }
        }
    }
</script>
<style lang="scss">
@import '~@/sass/main.scss';
#app {
  font-family: 'Source Sans Pro', Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

body {
  margin: 0;
  background: var(--va-background);
}
</style>
 