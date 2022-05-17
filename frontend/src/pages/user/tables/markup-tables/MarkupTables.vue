<template>
  <div class="markup-tables flex ml-4 mt-5">
    <!-- <div>
      <va-date-input
        class="mb-4 mt-4"
        label="placeholder"
        placeholder="날짜를 선택해주세요"
        clearable
        @click="getcctvlist"
        v-model="value"
        highlight-weekend
      />
    </div> -->
    <va-card :title="$t('tables.stripedHoverable')">
      <va-card-content>
        <div class="table-wrapper">
          <table
            class="va-table va-table--striped va-table--hoverable va-table--clickable"
          >
            <thead>
              <tr>
                <th>탐지일시</th>
                <th>탐지종류</th>
                <th>위치</th>
                <th>자세히</th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="li in issueList" :key="li.createdAt">
                <td>{{ li.createdAt.replace(".000000", "") }}</td>
                <td>{{ li.danger }}</td>
                <td>{{ li.location }}</td>
                <td>
                  <va-badge
                    text="바로가기"
                    color="success"
                    v-on:click="tableDetail(li)"
                  />
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="row justify--center">
          <va-pagination
            v-model="value"
            :pages="6"
            :visible-pages="4"
            class="mt-5 mb-4"
          />
        </div>
      </va-card-content>
    </va-card>
  </div>
</template>

<script>
import data from "@/data/tables/markup-table/data.json";
import { mapMutations } from "vuex";
import http from "@/components/common/axios.js";

export default {
  data() {
    return {
      listGetters: data.slice(0, 8),
      value: 0,
      issueData: undefined,
      issueList: undefined,
      start: 0,
      end: 5,
    };
  },
  mounted() {
    http
      .get("/cctv/list")
      .then((res) => {
        console.log(res);
        this.issueData = res.data.cctvList;
        console.log(this.issueData);
        console.log(res.data.cctvList);
        this.issueList = this.issueData.slice(
          0 + this.value * 7,
          7 + this.value * 7
        );
      })
      .catch((err) => {
        console.log(err);
      });
  },
  computed: {
    listGetters() {
      return this.$store.getters["getList"];
    },
  },
  methods: {
    tableDetail(li) {
      this.$store.state.detailList.createdAt = li.createdAt;
      this.$store.state.detailList.danger = li.danger;
      this.$store.state.detailList.location = li.location;
      this.$store.state.detailList.video_URL = li.video_URL;

      console.log(this.$store.state.detailList.createdAt);
      this.$router.push({ name: "tableDetail" });
    },

    getcctvlist() {
      console.log(this.value);
      http
        .post("/cctv/find/list", {
          dateTime: this.value,
        })
        .then((res) => {
          console.log(res.data);
          this.$store.state.cctvList = res.data.cctvList;
          console.log(this.$store.state.cctvList);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    value: function (hook) {
      this.issueList = this.issueData.slice(
        0 + (hook - 1) * 7,
        7 + (hook - 1) * 7
      );
    },
  },
};
</script>

<style lang="scss">
.markup-tables {
  .table-wrapper {
    overflow: auto;
  }

  .va-table {
    width: 100%;
  }

  .va-table-responsive {
    overflow: auto;
  }
}
</style>
