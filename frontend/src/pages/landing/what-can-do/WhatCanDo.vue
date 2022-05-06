<template>
  <div>
    <p class="f4">우리A.I는 무엇을 할 수 있나요?</p>
    <va-button-toggle
      outline
      outline-color=#49b37c
      v-model="service"
      :options="options"
      color=#49b37c
      size=large
      class="mb-4"
    />
    <div class="">
      <div class="d-flex justify--end"><img src="@/../public/img/landing-page/main-service/ChildThinking.png" alt="thinking" class="thinking"></div>
      <img
        :src="services[service][0]"
        alt="notification"
        class="main-service-image"
      >
    </div>
    <p class="mt-4">{{ services[service][1] }}</p>
  </div>
</template>

<script>
export default {
  name: 'what-can-do',

  data() {
    return {
      imageURL: require('@/../public/img/landing-page/main-service/Service1.png'),
      textContent: 'service 1',
      options: [
        { label: '위험상황 인식', value: 0 },
        { label: '위험정보 문자알림', value: 1 },
        { label: '관리자 페이지', value: 2 },
      ],
      service: 0,
      services: [
        [
          require('@/../public/img/landing-page/main-service/Service1.png'),
          '11111111111111111111111'
        ],
        [
          require('@/../public/img/landing-page/main-service/Service2.png'),
          '22222222222222222222222'
        ],
        [
          require('@/../public/img/landing-page/main-service/Service3.png'),
          '33333333333333333333333333333333'
        ],
      ]
    }
  },
}
</script>

<style lang="scss">

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
