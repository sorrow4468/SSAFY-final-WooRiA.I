<template>
  <form @submit.prevent="emailsubmit()">
    <!-- 아이디 -->
    <div class="row">
          <va-input
      class="mb-3"
      v-model="email"
      type="email"
      :label="$t('auth.email')"
      :error="!!emailErrors.length"
      :error-messages="emailErrors"
    />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="emailsubmit" class="my-0" style="border-radius:10px;"
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
      v-model="passwordCheck"
      type="password"
      :label="$t('auth.password_check')"
      :error="!!passwordCheckErrors.length"
      :error-messages="passwordCheckErrors"
    />

    <!-- 전화번호 -->
    <div class="row">
      <va-input
        class="mb-3"
        v-model="phoneNumber"
        type="phone_number"
        :label="$t('auth.phone_number')"
        :error="!!phoneNumberErrors.length"
        :error-messages="phoneNumberErrors"
        :success="!!successMessage.length"
        :success-messages="successMessage"

      />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="phonesubmit" class="my-0" style="border-radius:10px;"
          >인증번호 발송</va-button
        >
      </div>
    </div>

    <!-- 인증번호 확인 -->
    <div class="row">
      <va-input
        class="mb-3"
        v-model="numbercheck"
        type="number_check"
        :label="$t('auth.number_check')"
        :error="!!numbercheckerr.length"
        :error-messages="numbercheckerr"
      />
      <div class="d-flex justify--center ml-3 mb-3">
        <va-button @click="certnumsubmit" class="my-0" style="border-radius:10px;"
          >인증번호 확인</va-button
        >
      </div>
    </div>
        <div
      class="auth-layout__options d-flex align--center justify--space-between"
    >
      <va-checkbox
        v-model="agreedToTerms"
        class="mb-0"
        :error="!!agreedToTermsErrors.length"
        :errorMessages="agreedToTermsErrors"
        style="visibility:hidden;"
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
    
    <!-- 아이디 중복 모달 -->
    <va-modal
      v-model="idComfirm"
      hide-default-actions
      overlay-opacity="0.2"
    >
      <template #header>
        <h2>이메일 중복 확인</h2>
      </template>
      <div style="margin-top : 5px">사용할 수 있는 아이디 입니다.</div>
      <template #footer>
          <va-button class="timer-mg" @click="idComfirm = !idComfirm">
            사용하기            
          </va-button>
          <va-button class="timer-mg" @click="cancelId">
            취소          
          </va-button>
      </template>
    </va-modal>
    <!-- 문자 전송 모달 -->
    <va-modal
      v-model="sendMessage"
      hide-default-actions
      overlay-opacity="0.2"
    >
      <template #header>
        <h2>시간 설정</h2>
      </template>
      <div style="margin-top : 5px">{{setTimecustom}}{{ startMessage }}</div>
      <template #footer>
          <va-button class="timer-mg" @click="startTimer">
            시작
          </va-button>
          <va-button class="timer-mg" @click="timerStop">
            취소
          </va-button>
      </template>
    </va-modal>
    <!-- 문자 확인 모달 -->
    <va-modal
      v-model="messageComfrim"
      hide-default-actions
      overlay-opacity="0.2"
    >
      <template #header>
        <h2>알림</h2>
      </template>
      <div style="margin-top : 5px">성공적으로 확인되었습니다</div>
      <template #footer>
          <va-button class="timer-mg" @click="messageComfrim = !messageComfrim">
            확인
          </va-button>

      </template>
    </va-modal>


  </form>
</template>

<script>
// import axios from 'axios'
import http from '@/components/common/axios.js'

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
      emailSuccess: false,
      agreedToTermsErrors: [],
      numbercheck:[],
      numbersuccess: false,
      numbercheckerr:[],
      idComfirm:false,
      sendMessage:false,
      messageComfrim:false,
      successMessage : [],
    };
  },
  methods: {
    onsubmit() {
      this.idErrors = this.id ? [] : ["아이디를 적어주세요"];
      // this.emailSuccess = this.emailErrors ? [] : ["이메일 중복 체크를 해주세요"];
      // if (this.emailSuccess != []) {
      //   this.emailErrors = ["이메일 중복 체크를 해주세요"]
      // }
      // if (this.numbersuccess != []) {
      //   this.numbercheckerr = ["인증번호를 입력해 주세요"]
      // }
      this.passwordErrors = this.password ? [] : ["비밀번호를 적어주세요"];
      if (this.password != this.passwordCheck) {
        this.passwordCheckErrors = ["비밀번호를 확인해주세요"]
      }
      this.phoneNumberErrors = this.phoneNumber
        ? []
        : ["전화번호를 적어주세요"];
      this.emailErrors = this.email ? [] : ["이메일을 적어주세요"];
      // this.agreedToTermsErrors = this.agreedToTerms
      //   ? []
      //   : ["계속하려면 사용 약관에 동의해야 합니다"];
      if (!this.formReady) {

        return;
      }
      if (!this.emailSuccess)
      {
        this.emailErrors = ["이메일 중복체크를 해주세요"]
      } else if (!this.numbersuccess) {
          this.numbercheckerr = ["인증번호를 확인해 주세요"]
      } else {
            http.post(
            '/auth/signup',
            {
              "email": this.email,
              "nickname": this.email,
              "password": this.password,
              "phone": this.phoneNumber
            }
          ).then((res)=>{
            alert('회원가입 성공');
            this.$router.push({ name: "userboard" });

            }
          ).catch((err) => {
}
          )
      }



    },
    //  이메일 검증
    emailsubmit() {
      this.emailErrors = [];
      if (!this.email) {
        this.emailErrors.push('이메일을 입력하세요.');
      } else if (!this.validEmail(this.email)) {  // 이메일 형식이 아닐경우
        this.emailErrors.push('유효한 이메일이 아닙니다');
      } else {
        http.post(
        'auth/email/confirms',
         {
           "email" : this.email
                }
      ).then((res)=>{
        this.emailSuccess = true;
        this.idComfirm = true;
        }
      ).catch((err) => {
        this.emailErrors.push('이미 사용중인 아이디 입니다.');

      }
      )
      }
      // axios 요청 보내서
      if (!this.formReady) {
        return;
      }
    },
    // 인증번호 전송
    phonesubmit() {
       this.phoneNumberErrors = [];
      if (!this.phoneNumber) {
        this.phoneNumberErrors.push('핸드폰을 입력해 주세요.');
      } else if (!this.validphoneNumber(this.phoneNumber)) {  // 이메일 형식이 아닐경우
        this.phoneNumberErrors.push('유효한 번호가 아닙니다');
      } else {
        this.successMessage.push('성공적으로 전송하였습니다');
        http.post(
        'auth/sms/sends',
         {
           "to" : this.phoneNumber
                }
      ).then((res)=>{

        }
      ).catch((err) => {
       }
      )
      }
      // axios 요청 보내서
      if (!this.formReady) {
        return;
      }
    },
    // 인증번호 확인
    certnumsubmit() {
       this.numbercheckerr = [];
      if (!this.phoneNumber) {
        this.numbercheckerr.push('인증번호를 입력해 주세요.');
      } else {
        http.post(
        'auth/sms/confirms',
         {
           "to" : this.phoneNumber,
           "number" : this.numbercheck
                }
      ).then((res)=>{
        this.numbersuccess = true;
        this.messageComfrim = true;
        }
      ).catch((err) => {
        this.numbercheckerr.push('유효한 번호가 아닙니다');

        })
      }
      // axios 요청 보내서
      if (!this.formReady) {
        return;
      }
    },
    validEmail: function (email) {
      const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    validphoneNumber: function (phoneNumber) {
      const regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
      return regPhone.test(phoneNumber);
    },
    cancelId() {
      this.email = '';
      this.idComfirm = false;
    }
  },
  computed: {
    formReady() {
      return !(
        this.emailErrors.length ||
        this.passwordErrors.length ||
        this.passwordCheckErrors.length ||
        this.phoneNumberErrors.length ||
        this.agreedToTermsErrors.length
      );
    }
  }
};
</script>

<style lang="scss">
  .timer-mg {
    margin: 0px 10px;
  }
</style>
