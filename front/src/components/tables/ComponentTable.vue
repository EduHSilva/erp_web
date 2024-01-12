<script lang="ts">
import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";

export default {
  components: {ComponentActionsTable},
  props: {
    tableHeader: Array<String>,
    tableData: Array<Object>
  }
}
</script>

<template>
  <div class="container">
    <div class="table-responsive" v-if="tableData.length != 0">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col" v-for="i in tableHeader">{{ $t(i) }}</th>
        </tr>
        </thead>
        <tbody>
          <slot>
          </slot>
        </tbody>
      </table>
    </div>
    <div v-else>
      <div class="w-100 d-flex justify-content-center fw-bold">
        {{ $t("noData") }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  margin-top: 50px;
}

th {
  font-weight: bold;
}
</style>