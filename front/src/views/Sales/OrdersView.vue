<script setup lang="ts">
import {useOrderStore} from "@/store/modules/sales/ordersModule";

const store = useOrderStore();
store.get(0);
</script>
<script lang="ts">
import ComponentPagination from "@/components/ComponentPagination.vue";
import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";
import ComponentTable from "@/components/tables/ComponentTable.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";
import util from "@/mixins/util";

export default {
  components: {
    ComponentPagination,
    ComponentActionsTable, ComponentTable, ComponentSidebarInner, ComponentHeader, ModalConfirm
  },
  data() {
    return {
      text: ["sales", "orders", "list"],
      tableHeader: ["order", "status", "total", "actions"],
      deleteId: ""
    }
  },
  mixins: [util],
  methods: {
    openEdit(id: string) {
      this.$router.push({path: "/sales/order/" + id})
    },
    openConfirmDeleteModal(id: string) {
      this.deleteId = id;
    }
  }
}
</script>
<template>
  <ModalConfirm @delete="store.delete(deleteId)"/>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner title="orders" :links="store.links"/>
    </div>
    <div class="col-8 card">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("orders") }}</h2>
      </div>
      <div class="card-body">
        <ComponentTable :table-header="tableHeader" :table-data="store.ordersList">
          <tr v-for="order in store.ordersList">
            <td>{{ order.id }}</td>
            <td><span class="badge" :class="order.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(order.status, $t) }}
          </span></td>
            <td>{{ order.total }}</td>
            <td>{{ order.client }}</td>
            <ComponentActionsTable :modal="false" @edit="openEdit" @delete="openConfirmDeleteModal" :data="order"/>
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