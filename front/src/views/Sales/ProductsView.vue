<script setup lang="ts">
import { useProductStore } from "@/store/modules/sales/productModule";

const store = useProductStore();
store.get(0);
</script>

<script lang="ts">
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentPagination from "@/components/ComponentPagination.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentActionsTable from "@/components/tables/ComponentActionsTable.vue";
import ComponentTable from "@/components/tables/ComponentTable.vue";
import util from "@/mixins/util";

export default {
  components: {
    ComponentPagination,
    ComponentActionsTable, ComponentTable, ComponentSidebarInner, ComponentHeader, ModalConfirm
  },
  data() {
    return {
      text: ["sales", "products", "list"],
      tableHeader: ["name", "status", "price", "stock", "actions"],
      deleteId: ""
    }
  },
  mixins: [util],
  methods: {
    openEdit(id: string) {
      this.$router.push({ path: "/sales/product/" + id })
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
      <ComponentSidebarInner title="products" :links="store.links"/>
    </div>
    <div class="col-8 card">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("products") }}</h2>
      </div>
      <div class="card-body">
        <ComponentTable :table-header="tableHeader" :table-data="store.productList">
          <tr v-for="product in store.productList" :key="product.id">
            <td>{{ product.name }}</td>
            <td><span class="badge" :class="product.status == 'ACTIVE' ? 'text-bg-success' : 'text-bg-danger'">
            {{ formatStatus(product.status, $t) }}
          </span></td>
            <td> {{ $n(product.price, 'currency') }}</td>
            <td>{{ product.stock }}</td>
            <ComponentActionsTable :modal="false" @edit="openEdit" @delete="openConfirmDeleteModal" :data="product"/>
          </tr>
        </ComponentTable>
      </div>
      <div class="card-footer">
        <ComponentPagination @changePagination="store.get" :total-pages="store.totalPages" :page="store.page" back="sales"/>
      </div>
    </div>
  </main>
</template>

<style scoped>

</style>