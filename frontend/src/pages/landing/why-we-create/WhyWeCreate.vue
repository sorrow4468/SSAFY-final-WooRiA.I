<template>
  <div>
    <div class="f4">우리A.I는 왜 만들어졌나요?</div>
    <div class="d-flex justify-space-between">
      <div class="card-left">
        <img src="@/../public/img/landing-page/ChildDanger.png" alt="dangerous child" class="example-image">
      </div>
      <div class="card-right justify-center">
        <div class="subtitle">아이는 미래이니까</div>
        <div class="detail align-items-center">
          <p class="mt-4 mb-4">”아이는  밖에 나가서 뛰고 노는게 일이다” 라는 말처럼</p>
          <p class="mt-4 mb-4">모든 아이는 몸을 움직이고 놀면서 세상을 배웁니다</p>
          <p class="mt-4 mb-4">하지만, 아이의 호기심보다도 밖은 많이 위험하며</p>
          <p class="mt-4 mb-4">언제나 부모님의 관심과 시선이 필요합니다</p>
        </div>
      </div>
    </div>
    <hr style="margin:3rem;">
    <div class="d-flex justify-space-end">
      <div class="card-left">
        <div class="subtitle">늘 곁에 있어주고 싶으니까</div>
        <div class="detail">
          <p class="mt-4 mb-4">모든 부모는 늘 마음만은 아이 곁에 있을 것입니다</p>
          <p class="mt-4 mb-4">하지만 불가피하게 아이의 곁을 지킬 수 없을 때</p>
          <p class="mt-4 mb-4">아이가 위험하지는 않은지 걱정되시는 모든분께</p>
          <p><span style="font-size:1.7rem; font-weight:bold;">우리A.I</span>가 도움이 되었으면 합니다</p>
        </div>
      </div>
      <div class="card-right justify-center">
        <img src="@/../public/img/landing-page/ForChild.png" alt="dangerous child" class="example-image">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'why-we-create',
}
</script>

<style>
.example-image {
  width: 30rem;
  box-shadow: 3px 3px 10px #a8a8a8;
}

.subtitle {
  color: #52d08f;
  font-weight: bold;
  font-size: 2rem;
  text-shadow: 1px 1px 3px #a8a8a8;
}

.card-left {
  width: 50vh;
}

.card-right {
  width: 50vh;
}

.detail {
  font-size: 1.2rem;
}

</style>
