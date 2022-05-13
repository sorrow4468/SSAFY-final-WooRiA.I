<template>
  <div class="container">
    <div class="border">
      <div class="mt-3 ml-5">
        <va-button
          @click="start"
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="color: rgb(0, 78, 193); border-color: rgba(86, 189, 233, 1); background: rgba(0, 0, 0, 0);"
          ><p>일시</p></va-button
        >

        <va-button
          @click="start"
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="color: rgb(0, 78, 193); border-color: rgba(86, 189, 233, 1); background: rgba(0, 0, 0, 0);"
          ><p>번호</p></va-button
        >

        <va-button
          @click="start"
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="color: rgb(0, 78, 193); border-color: rgba(86, 189, 233, 1); background: rgba(0, 0, 0, 0);"
          ><p>상황</p></va-button
        >

        <va-button
          @click="start"
          class="va-button va-button--outline va-button--normal  haha"
          style="color: rgb(0, 78, 193); border-color: rgba(86, 189, 233, 1); background: rgba(0, 0, 0, 0);"
          ><p>내용</p></va-button
        >
      </div>

      <div class="haha mt-3 ml-5">
        <va-button
          @click="home"
          class="va-button va-button--outline va-button--normal mx-4 mb-2"
          style="border-radius:10px; width:100px; color: rgb(21, 78, 193); border-color: rgb(21, 78, 193); background: rgba(0, 0, 0, 0);"
          >홈으로</va-button
        >
        <va-button
          @click="out"
          class="va-button va-button--outline va-button--normal mx-4 mb-2"
          style="border-radius:10px; width:100px; color: rgb(228, 34, 34); border-color: rgb(228, 34, 34); background: rgba(0, 0, 0, 0);"
          >나가기</va-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "detailInfo",
  data() {}
};
</script>
<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}

.secs {
  font-size: 4em;
}
strong {
  color: blue;
}
p {
  font-family: "Lucida Sans", sans-serif;
  font-size: 20px;
  color: black;
}
.border {
  display: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 70%;
}

.haha {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 95%;
  height: 90px;
  margin-left: 5%;
}
</style>
