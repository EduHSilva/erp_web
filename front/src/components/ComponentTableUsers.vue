<script lang="ts">
import type User from "../store/types/user"
import util from '@/mixins/util'
import ComponentActionsTable from "@/components/ComponentActionsTable.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";

export default {
  components: {ModalConfirm, ComponentActionsTable},
  props: {
    users: {
      type: Array as () => User[],
      required: true,
    },
    deleteAction: Function,
    edit: Function
  },
  mixins: [util],

}
</script>

<template>
  <div class="container">
    <div class="table-responsive" v-if="users.length != 0">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col">{{ $t("name") }}</th>
          <th scope="col">{{ $t("email") }}</th>
          <th scope="col">{{ $t("status") }}</th>
          <th scope="col">{{ $t("profile") }}</th>
          <th scope="col">{{ $t("dateCreated") }}</th>
          <th scope="col">{{ $t("actions") }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in users">
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td><span class="badge" :class="user.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(user.status, $t) }}
          </span></td>
          <td>{{ user.profile != null ? user.profile.name : "" }}</td>
          <td>{{ formatDate(user.dateCreated) }}</td>
          <ComponentActionsTable :modal="false" @edit="edit" @delete="deleteAction" :data="user"/>
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
.container {
  margin-top: 50px;
}

th {
  font-weight: bold;
}

</style>