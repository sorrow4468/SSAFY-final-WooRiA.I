<template>
  <div>
    <p class="f4">우리A.I는 무엇을 할 수 있나요?</p>
    <div class="">
      <va-button class="mr-3 ml-3 example-button" color="success">위험상황 인식</va-button>
      <va-button class="mr-3 ml-3 example-button" color="white">위험정보 문자알림</va-button>
      <va-button class="mr-3 ml-3 example-button">관리자 페이지</va-button>
    </div>
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
  font-size: 1.5rem;

  &__on {
    background-color: #64D89D;
    color: white
  }
  &__off {
    color: white;
  }
}

</style>
