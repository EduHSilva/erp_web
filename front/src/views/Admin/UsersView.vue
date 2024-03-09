<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentPagination from "@/components/ComponentPagination.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";
import ComponentTable from "@/components/tables/ComponentTable.vue";
import { useUserStore } from "@/store/modules/admin/userModule";

const store = useUserStore()
store.get(0)
</script>

<script lang="ts">
import util from "@/mixins/util";

export default  {
  data() {
      return {
        text: ["admin", "users", "list"],
        deleteId: "",
        tableHeader: ["name", "email", "status", "profile", "dateCreated", "actions"]
      }
  },
  methods: {
    openEdit(id: string) {
      this.$router.push({ path: '/admin/user/' + id })
    },
    openConfirmDeleteModal(id: string) {
      this.deleteId = id;
    },
  },
  mixins: [util]
}
</script>

<template>
  <ModalConfirm @delete="store.delete(deleteId)"/>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner  :links="store.links" title="users"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("userInfo")}}</h2>
      </div>
      <div class="card-body">
        <ComponentTable :table-header="tableHeader" :table-data="store.userList">
          <tr v-for="user in store.userList" :key="user.id">
            <td>{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td><span class="badge" :class="user.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(user.status, $t) }}
          </span></td>
            <td>{{ user.profile != null ? user.profile.name : "" }}</td>
            <td>{{ $d(Date.parse(user.dateCreated.toString())) }}</td>
            <ComponentActionsTable :modal="false" @edit="openEdit" @delete="openConfirmDeleteModal" :data="user"/>
          </tr>
        </ComponentTable>
      </div>
      <div class="card-footer">
        <ComponentPagination :total-pages="store.totalPages" :page="store.page" back="admin"/>
      </div>
    </div>
  </main>
</template>


<style scoped>
hr {
  stroke-width: 1px;
  stroke: #E8E8E8;
}

button {
  border-radius: 8px;
  border: 1px solid #E8E8E8;
  background: #111;
  color: #fff;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.06);
  float: right;
  font-feature-settings: 'clig' off, 'liga' off;
  display: flex;
  padding: 8px 12px;
  align-items: center;
}


</style>