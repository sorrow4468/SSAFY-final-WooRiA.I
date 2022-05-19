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
    <p v-for="text in services[service][1]" :key="text.id" class="mt-4 f5">{{ text }}</p>
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
        { label: '대시보드 페이지', value: 2 },
      ],
      service: 0,
      services: [
        [
          require('@/../public/img/landing-page/main-service/Service1.png'),
          [
            '인공지능을 통해 위험상황을 감지합니다',
            '',
          ]
        ],
        [
          require('@/../public/img/landing-page/main-service/Service2.png'),
          [
            '위험정보를 감지할 경우, 등록된 전화번호로 알림문자를 발송합니다',
            '',
          ]
        ],
        [
          require('@/../public/img/landing-page/main-service/Service3.png'),
          [
            '감지된 위험정보들의 통계를 제공합니다',
            '일간, 주간, 월간, 그리고 지역별 놀이터의 위험상황 발생률을 확인할 수 있습니다',
            '감지한 위험상황의 상세정보와, 녹화된 감지당시 영상을 볼 수 있습니다',
          ]
        ],
      ]
    }
  },
}
</script>

<style lang="scss">

.main-service-image {
  width: 70vw;
  max-height: 700px;
  object-fit: scale-down;
  margin-top: 3rem;
  border-radius: 1rem;
  // box-shadow: 1px 1px 1px #cccccc;
}

.thinking {
  width: 200px;
  position: absolute;
  z-index: -1;
  margin-top: -200px;
  right: 15vw;
}

.f5 {
  font-size: 1vw;
  color: #434343;
  font-weight: bold;
}
</style>
