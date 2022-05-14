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
                <th>다운로드</th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="li in listGetters" :key="li.createdAt">
                <td>{{ li.createdAt }}</td>
                <td>{{ li.danger }}</td>
                <td>{{ li.location }}</td>
                <td>{{ li.video_URL }}</td>
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
