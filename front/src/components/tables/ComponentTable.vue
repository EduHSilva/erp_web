<script lang="ts">

export default {
  props: {
    tableHeader: {
      type: Array<string>,
      required: true
    },
    tableData: {
      type: Array<Object>,
      required: true
    } 
  }
}
</script>

<template>
  <div class="container">
    <div class="table-responsive" v-if="tableData.length != 0">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col" v-for="i, k in tableHeader" :key="k">{{ $t(i) }}</th>
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

.table-responsive {
  min-height: 200px;
}

th {
  font-weight: bold;
}
</style>