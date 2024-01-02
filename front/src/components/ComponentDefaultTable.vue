<script lang="ts">
import util from '@/mixins/util'
import type Module from "@/store/types/module";
import ActionsTableComponent from "@/components/ComponentActionsTable.vue";

export default {
  components: {ActionsTableComponent},
  props: {
    data: {
      type: Array as () => Module[],
      required: true
    },
    modal: {
      type: Boolean,
      required: true
    },
    edit: {
      type: Function
    },
    deleteAction: {
      type: Function
    }
  },
  mixins: [util]
}
</script>

<template>
  <div class="container">
    <div class="table-responsive" v-if="data.length != 0">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col">{{ $t("name") }}</th>
          <th scope="col">{{ $t("dateCreated") }}</th>
          <th scope="col">{{ $t("actions") }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="d in data">
          <td>{{ d.name }}</td>
          <td>{{ formatDate(d.dateCreated) }}</td>
          <ActionsTableComponent :modal="modal" :data="d" @edit="edit" @delete="deleteAction"/>
        </tr>
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
th {
  font-weight: bold;
}
</style>