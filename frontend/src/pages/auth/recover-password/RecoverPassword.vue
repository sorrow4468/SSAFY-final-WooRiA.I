<template>
  <form @submit.prevent="onsubmit" class="login">
    <div class="mb-5">
      <!-- 아이디 -->
      <va-input
        class="mb-3"
        v-model="id"
        type="id"
        :label="$t('auth.id')"
        :error="!!emailErrors.length"
        :error-messages="idErrors"
      />
      <!-- 이메일 -->
      <va-input
        class="mb-3"
        v-model="email"
        type="email"
        :label="$t('auth.email')"
        :error="!!emailErrors.length"
        :error-messages="emailErrors"
      />
      <!-- 전화번호 -->
      <va-input
        class="mb-3"
        v-model="phone_number"
        type="phone_number"
        :label="$t('auth.phone_number')"
        :error="!!emailErrors.length"
        :error-messages="phoneNumberErrors"
      />
    </div>
    <div class="row justify--center">
      <va-button type="submit" class="my-0">{{
        $t("auth.reset_password")
      }}</va-button>
    </div>
  </form>
</template>

<script>
export default {
  name: "recover-password",
  data() {
    return {
      id: "",
      email: "",
      phoneNumber: "",
      idErrors: [],
      emailErrors: [],
      phoneNumberErrors: []
    };
  },
  methods: {
    onsubmit() {
      this.idErrors = this.id ? [] : ["아이디를 적어주세요"];
      this.emailErrors = this.email ? [] : ["이메일을 적어주세요"];
      this.phoneNumberErrors = this.phoneNumber
        ? []
        : ["전화번호를 적어주세요"];
      // if (!this.email) {
      //   this.emailErrors = ["이메일을 적어주세요"];
      // } else {
      //   this.$router.push("/");
      // }
    }
  }
};
</script>

<style lang="scss"></style>
