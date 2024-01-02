<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

import ComponentPagination from "@/components/ComponentPagination.vue";
import DefaultTable from "@/components/DefaultTable.vue";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";
import {useProfileStore} from "@/store/modules/profilesModule";

const store = useProfileStore()
store.get(0)
</script>
<script lang="ts">
export default {
  data() {
    return {
      text: ["admin", "profiles", "list"],
      editing: {
        id: "",
        name: ""
      },
      deleteId: '',
      links: []
    }
  },
  methods: {
    openEdit(id: string) {
      this.$router.push({ path: '/admin/profile/' + id })
    },
    openConfirmDeleteModal(id: string) {
      this.deleteId = id;
    },
  }
}
</script>

<template>
  <ModalConfirm @delete="store.delete(deleteId)"/>
  <ComponentToastSuccess message="saveSuccess"/>
  <ComponentToastError/>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="store.links" title="profiles"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("profiles") }}</h2>
      </div>
      <div class="card-body">
        <DefaultTable :modal="false" :data="store.profiles" @edit="openEdit" @delete="openConfirmDeleteModal"/>
      </div>
      <div class="card-footer">
        <ComponentPagination @changePagination="store.get" :total-pages="store.totalPages" :page="store.page"/>
      </div>
    </div>
  </main>
</template>


<style scoped>

hr {
  stroke-width: 1px;
  stroke: #E8E8E8;
}
</style>