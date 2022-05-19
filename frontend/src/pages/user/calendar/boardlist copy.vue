<template>
  <div class="markup-tables flex mt-5 ml-3">
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
                <td>{{ li.createdAt }}</td>
                <td>{{ li.danger }}</td>
                <td>{{ li.location }}</td>
                <td>
                  <va-badge
                    text="바로가기"
                    color="success"
                    v-on:click="detail(li)"
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
import http from "@/components/common/axios.js";

// 더미 데이터
import data from "@/data/tables/markup-table/data.json";
import { mapMutations } from "vuex";

export default {
  name: "boardlist",
  components: {},
  data() {
    return {
      users: data.slice(0, 4),
      value: 1,
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
          0 + this.value * 5,
          5 + this.value * 5
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
    detail(li) {
      this.$store.state.detailList.createdAt = li.createdAt;
      this.$store.state.detailList.danger = li.danger;
      this.$store.state.detailList.location = li.location;
      this.$store.state.detailList.video_URL = li.video_URL;

      console.log(this.$store.state.detailList.createdAt);
      this.$router.push({ name: "detail" });
    },
    getStatusColor(status) {
      if (status === "paid") {
        return "success";
      }

      if (status === "processing") {
        return "info";
      }
      return "danger";
    },
  },
  watch: {
    value: function (hook) {
      this.issueList = this.issueData.slice(0 + hook * 5, 5 + hook * 5);
    },
  },
};
</script>

<style lang="scss">
.markup-tables {
  width: 95%;
  height: 30px;
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
