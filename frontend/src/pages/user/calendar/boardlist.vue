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
              <tr v-for="li in listGetters" :key="li.createdAt">
                <td>{{ li.createdAt.replace(".000000", "") }}</td>
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
        <!-- <div class="row justify--center">
          <va-pagination
            v-model="value"
            :pages="6"
            :visible-pages="4"
            class="mt-5 mb-4"
          />
        </div> -->
      </va-card-content>
    </va-card>
  </div>
</template>

<script>
// 더미 데이터
import data from "@/data/tables/markup-table/data.json";
import { mapMutations } from "vuex";

export default {
  name: "boardlist",
  components: {},
  data() {
    return {
      users: data.slice(0, 4),
      value: 1
    };
  },
  computed: {
    listGetters() {
      return this.$store.getters["getList"];
    }
  },
  methods: {
    detail(li) {
      this.$store.state.detailList.createdAt = li.createdAt;
      this.$store.state.detailList.danger = li.danger;
      this.$store.state.detailList.location = li.location;
      this.$store.state.detailList.video_URL = li.video_URL;
      this.$store.state.config.url = li.video_URL;
      this.$store.state.config.id = "vs";
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
    }
  }
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
