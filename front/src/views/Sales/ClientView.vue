<script setup lang="ts">
  import {usePersonStore} from "@/store/modules/sales/personModule";
  import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
  import ComponentHeader from "@/components/ComponentHeader.vue";
  import ComponentPagination from "@/components/ComponentPagination.vue";
  import ComponentTable from "@/components/tables/ComponentTable.vue";
  import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";
  import ModalConfirm from "@/components/modais/ModalConfirm.vue";

  const store = usePersonStore();
  store.get(0);
</script>

<script lang="ts">
  import util from "@/mixins/util";

  export default {
    data() {
        return {
          text: ["sales", "clients", "list"],
          tableHeader: ["name", "email", "status", "dateCreated", "actions" ],
          deleteId: ''
        }
    },
    mixins: [util],
    methods: {
      openEdit(id: string) {
        this.$router.push({ path: '/sales/persons/' + id })
      },
      openConfirmDeleteModal(id: string) {
        this.deleteId = id;
      },
    },
  }
</script>

<template>
  <ModalConfirm @delete="store.delete(deleteId)"/>
  <ComponentHeader inner :text="text"/>
  <main>
      <div class="col-3">
        <ComponentSidebarInner title="clients" :links="store.links" />
      </div>
      <div class="col-8 card">
        <div class="card-header">
          <h2 class="text-center py-2">{{ $t("clients") }}</h2>
        </div>
        <div class="card-body">
         <ComponentTable :table-header="tableHeader" :table-data="store.clientList">
           <tr v-for="person in store.clientList">
             <td>{{ person.name }}</td>
             <td>{{ person.email }}</td>
             <td><span class="badge" :class="person.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(person.status, $t) }}
          </span></td>
             <td>{{ formatDate(person.dateCreated) }}</td>
             <ComponentActionsTable :modal="false" @edit="openEdit" @delete="openConfirmDeleteModal" :data="person"/>
           </tr>
         </ComponentTable>
        </div>
        <div class="card-footer">
          <ComponentPagination @changePagination="store.get" :total-pages="store.totalPages" :page="store.page"/>
        </div>
      </div>
    </main>
</template>

<style scoped>

</style>