<script setup lang="ts">
  import {usePersonStore} from "@/store/modules/sales/personModule";
  import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
  import ComponentHeader from "@/components/ComponentHeader.vue";
  import ComponentPagination from "@/components/ComponentPagination.vue";
  import ComponentTable from "@/components/tables/ComponentTable.vue";
  import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";
  import ModalConfirm from "@/components/modais/ModalConfirm.vue";

  const store = usePersonStore();
  store.getSellers(0);
</script>

<script lang="ts">
  import util from "@/mixins/util";

  export default {
    data() {
        return {
          text: ["sales", "sellers", "list"],
          tableHeader: ["name", "email", "status", "dateCreated" ],
        }
    },
    mixins: [util],
  }
</script>

<template>
  <ComponentHeader inner :text="text"/>
  <main>
      <div class="col-3">
        <ComponentSidebarInner title="sellers" :links="store.linksSeller" />
      </div>
      <div class="col-8 card">
        <div class="card-header">
          <h2 class="text-center py-2">{{ $t("sellers") }}</h2>
        </div>
        <div class="card-body">
         <ComponentTable :table-header="tableHeader" :table-data="store.sellersList">
           <tr v-for="person in store.sellersList">
             <td>{{ person.name }}</td>
             <td>{{ person.email }}</td>
             <td><span class="badge" :class="person.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(person.status, $t) }}
          </span></td>
             <td>{{ formatDate(person.dateCreated) }}</td>
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