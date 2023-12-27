<script lang="ts">
import type User from "../store/types/user"
import util from '@/mixins/util.js'

export default {
  props: {
    users: {
      type: Array as () => User[],
      required: true,
    }
  },
  mixins: [util]
}
</script>

<template>
  <div class="container">
    <div class="table-responsive">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col">{{ $t("name") }}</th>
          <th scope="col">{{ $t("email") }}</th>
          <th scope="col">{{ $t("status") }}</th>
          <th scope="col">{{ $t("profile") }}</th>
          <th scope="col">{{ $t("dateCreated") }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in users">
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
          <td><span class="badge text-bg-success">
            {{ formatStatus(user.status, $t) }}
          </span></td>
          <td>{{ user.profile }}</td>
          <td>{{ formatDate(user.dateCreated) }}</td>
        </tr>
        <tr v-if="users.length == 0">
          sem dados
        </tr>
        </tbody>
      </table>
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