<template>
  <div class="container">
    <div class="border">
      <div class="mt-5 ml-5">
        <br />
        <br />
        <br />
        <va-button
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="
            color: rgb(0, 78, 193);
            border-color: rgba(40, 99, 188);
            background: rgba(255, 255, 255);
          "
          ><p>일시 : {{ this.$store.state.detailList.createdAt }}</p></va-button
        >

        <va-button
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="
            color: rgb(0, 78, 193);
            border-color: rgba(40, 99, 188);
            background: rgba(255, 255, 255);
          "
          ><p>장소 : {{ this.$store.state.detailList.location }}</p></va-button
        >

        <va-button
          class="va-button va-button--outline va-button--normal mr-2 mb-4 haha"
          style="
            color: rgb(0, 78, 193);
            border-color: rgba(40, 99, 188);
            background: rgba(255, 255, 255);
          "
          ><p>상황 : {{ this.$store.state.detailList.danger }}</p></va-button
        >
      </div>

      <div class="haha mt-5 ml-5">
        <va-button
          @click="goHome()"
          class="va-button va-button--outline va-button--normal mx-2 mb-2"
          style="
            border-radius: 10px;
            width: 210px;
            color: rgb(255, 255, 255);
            border-color: rgb(0, 204, 0);
            background: rgb(0, 204, 0);
          "
          >홈으로</va-button
        >

        <va-button
          @click="goTable()"
          class="va-button va-button--outline va-button--normal mx-2 mb-2"
          style="
            border-radius: 10px;
            width: 210px;
            color: rgb(255, 255, 255);
            border-color: rgb(228, 34, 34);
            background: rgba(228, 34, 34);
          "
          >나가기</va-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "tableDetailInfo",
  data() {},
  methods: {
    goHome() {
      this.$router.push("/");
    },
    goTable() {
      this.$router.push("/user/userboard");
    },
  },
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
  height: 95px;
  margin-left: 5%;
}
</style>
