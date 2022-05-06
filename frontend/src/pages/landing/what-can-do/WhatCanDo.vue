<template>
  <div>
    <p class="f4">우리A.I는 무엇을 할 수 있나요?</p>
    <va-button-group outline color=#49b37c size=large class="mb-4">
      <va-button class="example-button__off">위험상황 인식</va-button>
      <va-button class="example-button__on">위험정보 문자알림</va-button>
      <va-button class="example-button__off">관리자 페이지</va-button>
    </va-button-group>
    <div class="">
      <div class="d-flex justify--end"><img src="@/../public/img/landing-page/main-service/ChildThinking.png" alt="thinking" class="thinking"></div>
      <img
        src="@/../public/img/landing-page/main-service/Notification.png"
        alt="notification"
        class="main-service-image"
      >
    </div>
    <p class="mt-4">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
  </div>
</template>

<script>
export default {
  name: 'what-can-do',
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
