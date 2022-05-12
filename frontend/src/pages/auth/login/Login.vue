<template>
  <form @submit.prevent="onsubmit">
    <va-input
      class="mb-3"
      v-model="email"
      type="email"
      :label="$t('auth.email')"
      :error="!!emailErrors.length"
      :error-messages="emailErrors"
    />

    <va-input
      class="mb-3"
      v-model="password"
      type="password"
      :label="$t('auth.password')"
      :error="!!passwordErrors.length"
      :error-messages="passwordErrors"
    />
        <canvas></canvas>
    <div
      class="auth-layout__options d-flex align--center justify--space-between"
    >
      <va-checkbox
        v-model="keepLoggedIn"
        class="mb-0"
        :label="$t('auth.keep_logged_in')"
      />
      <router-link class="ml-1 link" :to="{ name: 'recover-password' }">{{
        $t("auth.recover_password")
      }}</router-link>
    </div>

    <div class="d-flex justify--center mt-5">
      <!-- <va-button
        @click="onsubmit"
        class="my-0 mx-1"
        style="background:#FCFF5C; color:#434343; border-radius:10px; width:170px;"
      >
        카카오톡 로그인
      </va-button> -->
      <va-button
        @click="onsubmit"
        class="my-0 mx-1"
        style="border-radius:10px; width:170px;"
        >로그인</va-button
      >
    </div>
  </form>
</template>

<script>
import http from '@/components/common/axios.js'


export default {
  name: "login",
  data() {
    return {
      email: "",
      password: "",
      keepLoggedIn: false,
      emailErrors: [],
      passwordErrors: []
    };
  },
  mounted() {
            var client = new WebSocket('wss://k6e2021.p.ssafy.io/cctv1/');
            var canvas = document.querySelector('canvas');
            var jsmpeg = require('jsmpeg');
            var player = new jsmpeg(client, {
              canvas: canvas
            });
            console.log(player)
  },
  computed: {
    formReady() {
      return !this.emailErrors.length && !this.passwordErrors.length;
    }
  },
  methods: {
    onsubmit() {
      this.emailErrors = this.email ? [] : ["이메일을 적어주세요"];
      this.passwordErrors = this.password ? [] : ["비밀번호를 적어주세요"];
      if (!this.formReady) {
        return;
      } else{
        http.post(
            '/auth/login',
            {
              "email": this.email,
              "password": this.password
            }
          ).then((res)=>{
            window.localStorage.setItem('accessToken', res.data.accessToken);
            window.localStorage.setItem('refreshToken', res.data.refreshToken);
            alert('로그인 성공');
            this.$router.push({ name: "dashboard" });
            
            }
          ).catch((err) => {
            console.log(err)
            })
      }
    }
  }
};
</script>
