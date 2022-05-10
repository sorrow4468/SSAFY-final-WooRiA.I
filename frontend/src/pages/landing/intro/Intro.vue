<template>
  <div>
    <head class="d-flex justify--space-between mt-5">
      <img src="@/../public/img/landing-page/LandingPage.png" alt="wallpaper" class="wallpaper">
      <img src="@/../public/img/landing-page/Background.png" alt="background" class="background">
      <div class="d-flex align--center">
        <img src="@/../public/img/landing-page/woori-ai-logo/child-boy.png" alt="logo" class="main-logo">
        <div class="f1 mr-2 ml-2">우리A.I</div>
        <img src="@/../public/img/landing-page/woori-ai-logo/child-girl.png" alt="logo" class="main-logo">
      </div>
      <auth class="d-flex align--center">
        <a href="auth/signup/"><div class="f2">회원가입</div></a>
        <a href="auth/login/"><div class="ml-3 f2">로그인</div></a>
      </auth>
    </head>
    <div class="content">
      <div class="f3">부모마음이</div>
      <div class="f3">다 그런거니까</div>
      <a href="user/userboard/"><va-button class="start-button" color="success">시작하기</va-button></a>
    </div>
    <img src="@/../public/img/landing-page/ScrollDown.png" alt="scroll-down" class="scroll-down">
  </div>
</template>

<script>
export default {
  name: 'intro',

}
</script>

<style lang="scss">
  .content {
    margin-top: 25rem;
  }

  .start-button {
    width: 15rem;
    height: 3rem;
    margin-top: 1rem;
    font-size: 1.5rem;
  }

  .f1 {
    font-weight: bold;
    font-size: 2rem;
    color: white;
    text-shadow: 3px 3px 3px #434343;
  }

  .f2 {
    font-weight: bold;
    font-size: 1.25rem;
    color: white;
    text-shadow: 3px 3px 3px #434343;
  }

  .f3 {
    color: white;
    font-size: 3rem;
    margin: 1.25rem;
    font-weight: bold;
    text-shadow: 3px 3px 3px #434343;
  }

  .main-logo {
    width: 2.5rem;
    filter: drop-shadow(3px 3px 3px #434343);
  }

  .wallpaper {
    width: 100%;
    position: absolute;
    z-index: -1;
    top: -50px;
    left: 0%;
    height: 92%;
    max-height: 1080px;
    object-fit: cover;
  }

  .background {
    z-index: -2;
    position: absolute;
    width: 100%;
    left: 0%;
    top: -90%;
  }

  .scroll-down {
    width: 10%;
    margin-top: 5rem;
  }

</style>
