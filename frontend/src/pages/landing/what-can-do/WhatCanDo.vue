<template>
  <div>
    <p class="f4">우리A.I는 무엇을 할 수 있나요?</p>
    <div class="">
      <button class="example-button example-button__off mr-3 ml-3">위험상황 인식</button>
      <button class="example-button example-button__on mr-3 ml-3">위험정보 문자알림</button>
      <button class="example-button example-button__off mr-3 ml-3">관리자 페이지</button>
    </div>
    <div class="mt-4">
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
  width: 15rem;
  height: 3rem;
  margin-top: 1rem;
  font-size: 1.25rem;
  border-radius: 2rem;
  border: 0;
  outline: 0;
  box-shadow: 2px 2px 2px #a8a8a8;
  font-weight: bold;

  &__on {
    background-color: #64D89D;
    color: white;
    text-shadow: 2px 2px 2px #a8a8a8;
  }
  &__off {
    color: #434343;
    background-color: rgb(244, 244, 244);
  }

}

.main-service-image {
  width: 100%;
}

</style>
