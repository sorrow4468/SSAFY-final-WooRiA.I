<template>
  <form @submit.prevent="onsubmit()">
    <!-- 아이디 -->
    <div class="row">
      <va-input
        class="mb-3"
        v-model="id"
        type="id"
        :label="$t('auth.id')"
        :error="!!idErrors.length"
        :error-messages="idErrors"
      />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="onsubmit" class="my-0" style="border-radius:10px;"
          >중복확인</va-button
        >
      </div>
    </div>
    <!-- 비밀번호 -->
    <va-input
      class="mb-3"
      v-model="password"
      type="password"
      :label="$t('auth.password')"
      :error="!!passwordErrors.length"
      :error-messages="passwordErrors"
    />

    <!-- 비밀번호 확인 -->

    <va-input
      class="mb-3"
      v-model="password_check"
      type="password_check"
      :label="$t('auth.password_check')"
      :error="!!passwordCheckErrors.length"
      :error-messages="passwordCheckErrors"
    />

    <!-- 전화번호 -->
    <div class="row">
      <va-input
        class="mb-3"
        v-model="phone_number"
        type="phone_number"
        :label="$t('auth.phone_number')"
        :error="!!phoneNumberErrors.length"
        :error-messages="phoneNumberErrors"
      />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="onsubmit" class="my-0" style="border-radius:10px;"
          >인증번호 발송</va-button
        >
      </div>
    </div>

    <!-- 인증번호 확인 -->
    <div class="row">
      <va-input
        class="mb-3"
        v-model="number_check"
        type="number_check"
        :label="$t('auth.number_check')"
        :error="!!phoneNumberErrors.length"
        :error-messages="phoneNumberErrors"
      />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="onsubmit" class="my-0" style="border-radius:10px;"
          >인증번호 확인</va-button
        >
      </div>
    </div>

    <!-- 이메일 -->
    <va-input
      class="mb-3"
      v-model="email"
      type="email"
      :label="$t('auth.email')"
      :error="!!emailErrors.length"
      :error-messages="emailErrors"
    />

    <div
      class="auth-layout__options d-flex align--center justify--space-between"
    >
      <va-checkbox
        v-model="agreedToTerms"
        class="mb-0"
        :error="!!agreedToTermsErrors.length"
        :errorMessages="agreedToTermsErrors"
      >
        <template #label>
          <span class="ml-1">
            {{ $t("auth.agree") }}
            <span class="link">{{ $t("auth.termsOfUse") }}</span>
          </span>
        </template>
      </va-checkbox>
      <router-link class="ml-1 link" :to="{ name: 'recover-password' }">
        {{ $t("auth.recover_password") }}
      </router-link>
    </div>

    <div class="d-flex justify--center mt-3">
      <va-button @click="onsubmit" class="my-0">{{
        $t("auth.sign_up")
      }}</va-button>
    </div>
  </form>
</template>

<script>
export default {
  name: "signup",
  data() {
    return {
      id: "",
      password: "",
      passwordCheck: "",
      phoneNumber: "",
      email: "",
      agreedToTerms: false,
      idErrors: [],
      passwordErrors: [],
      passwordCheckErrors: [],
      phoneNumberErrors: [],
      emailErrors: [],
      agreedToTermsErrors: []
    };
  },
  methods: {
    onsubmit() {
      this.idErrors = this.id ? [] : ["아이디를 적어주세요"];
      this.passwordErrors = this.password ? [] : ["비밀번호를 적어주세요"];
      this.passwordCheckErrors = this.passwordCheck
        ? []
        : ["비밀번호를 확인해주세요"];
      this.phoneNumberErrors = this.phoneNumber
        ? []
        : ["전화번호를 적어주세요"];
      this.emailErrors = this.email ? [] : ["이메일을 적어주세요"];
      this.agreedToTermsErrors = this.agreedToTerms
        ? []
        : ["계속하려면 사용 약관에 동의해야 합니다"];
      if (!this.formReady) {
        return;
      }
      this.$router.push({ name: "dashboard" });
    }
  },
  computed: {
    formReady() {
      return !(
        this.idErrors.length ||
        this.passwordErrors.length ||
        this.passwordCheckErrors.length ||
        this.phoneNumberErrors.length ||
        this.emailErrors.length ||
        this.agreedToTermsErrors.length
      );
    }
  }
};
</script>

<style lang="scss"></style>
