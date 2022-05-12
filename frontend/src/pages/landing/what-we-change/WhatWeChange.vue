<template>
  <div>
    <p class="f4">우리A.I가 바꿀 사회는 어떤 모습인가요?</p>
    <div class="d-flex justify--center">
      <div>
        <img src="@/../public/img/landing-page/persona/001.png" alt="persona" class="persona">
        <p class="mt-4">아이가 좋아하고</p>
      </div>
      <div class="v-line"></div>
      <div>
        <img src="@/../public/img/landing-page/persona/002.png" alt="persona" class="persona">
        <p class="mt-4">부모님이 좋아하고</p>
      </div>
      <div class="v-line"></div>
      <div>
        <img src="@/../public/img/landing-page/persona/003.png" alt="persona" class="persona">
        <p class="mt-4">한명은 누구?</p>
      </div>
    </div>
    <div style="height:45vw;"><img src="@/../public/img/landing-page/BackgroundBottom.png" alt="background-bottom" class="bg-bottom"></div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="scss">

.persona {
  width: 20vw;
  margin-left: 3vw;
  margin-right: 3vw;
  filter: drop-shadow(3px 3px 3px #cccccc);
}

.v-line {
  border-left : 2px solid #52d08f;
  height : 22vw;
}

.bg-bottom {
  position: absolute;
  left: 0%;
  width: 100%;
  z-index: -1;
}

</style>
