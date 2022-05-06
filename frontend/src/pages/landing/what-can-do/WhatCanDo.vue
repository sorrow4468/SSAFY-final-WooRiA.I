<template>
  <div>
    <p class="f4">우리A.I는 무엇을 할 수 있나요?</p>
    <va-button-toggle
      outline
      outline-color=#49b37c
      v-model="model"
      :options="options"
      color=#49b37c
      size=large
      class="mb-4"
    />
    <va-button-group outline color=#49b37c size=large class="mb-4">
      <va-button v-on:click="changeService(1)" class="example-button__on">위험상황 인식</va-button>
      <va-button v-on:click="changeService(2)" class="example-button__off">위험정보 문자알림</va-button>
      <va-button v-on:click="changeService(3)" class="example-button__off">관리자 페이지</va-button>
    </va-button-group>
    <div class="">
      <div class="d-flex justify--end"><img src="@/../public/img/landing-page/main-service/ChildThinking.png" alt="thinking" class="thinking"></div>
      <img
        :src="imageURL"
        alt="notification"
        class="main-service-image"
      >
    </div>
    <p class="mt-4">{{textContent}}</p>
  </div>
</template>

<script>
export default {
  name: 'what-can-do',
  // 버튼 함수 만들고, state만 바꿔주기, v-if 활용해서,
  // 클릭했을 때

  data() {
    return {
      stateNumber: 0,
      imageURL: require('@/../public/img/landing-page/main-service/Service1.png'),
      textContent: 'service 1',
      options: [
        { label: '위험상황 인식', value: 'one' },
        { label: '위험정보 문자알림', value: 'two' },
        { label: '관리자 페이지', value: 'three' },
      ],
      model: 'one',
    }
  },

  methods: {
    changeService(key) {
      if (key == 1) {
        this.imageURL = require('@/../public/img/landing-page/main-service/Service1.png')
        this.textContent = 'service1'
      } else if (key == 2) {
        this.imageURL = require('@/../public/img/landing-page/main-service/Service2.png')
        this.textContent = 'service2'
      } else if (key == 3) {
        this.imageURL = require('@/../public/img/landing-page/main-service/Service3.png')
        this.textContent = 'service3'
      } else {
        console.log(this.state.model)
      }
    }
  },
}
</script>

<style lang="scss">

.example-button {

  &__on {
    background-color: #49b37c;
    color: white;
    font-weight: bold;
    font-size: 1.5rem;
  }
  &__off {
    font-size: 1rem;
  }

}

.main-service-image {
  width: 70%;
  max-height: 700px;
  object-fit: cover;
  margin-top: 3rem;
  border-radius: 1rem;
  box-shadow: 1px 1px 1px #cccccc;
}

.thinking {
  width: 200px;
  position: absolute;
  z-index: -1;
  margin-top: -200px;
  right: 28rem;
}

</style>
