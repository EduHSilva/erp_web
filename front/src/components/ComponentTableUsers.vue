<script lang="ts">
import type User from "../store/types/user"

export default  {
  props: {
    users: {
      type: Array as () => User[],
      required: true,
    }
  }
}
</script>

<template>
  <div class="container">
    <div class="table-responsive">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col">Nome</th>
          <th scope="col">Email</th>
          <th scope="col">Status</th>
          <th scope="col">Perfil</th>
          <th scope="col">Data de Criação</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="user in users">
          <td>{{ user.name }}</td>
          <td>{{user.email}}</td>
          <td><span class="badge text-bg-success" >
            {{user.status}}
          </span></td>
          <td>{{user.profile}}</td>
          <td>{{user.dateCreated}}</td>
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