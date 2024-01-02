<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

import ComponentPagination from "@/components/ComponentPagination.vue";
import {useModulesStore} from "@/store/modules/modulesModule";
import ModalForm from "@/components/modais/ModalForm.vue";
import DefaultTable from "@/components/DefaultTable.vue";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import ModalConfirm from "@/components/modais/ModalConfirm.vue";

const store = useModulesStore()
store.get(0)
</script>
<script lang="ts">
export default {
  data() {
    return {
      text: ["admin", "modules", "list"],
      editing: {
        id: "",
        name: ""
      },
      deleteId: ''
    }
  },
  methods: {
    openModalSave() {
      this.editing = {
        id: "",
        name: ""
      }
    },
    openModalEdit(id: string, name: string) {
      this.editing = {
        id, name
      }
    },
    openConfirmDeleteModal(id: string) {
      this.deleteId = id;
    },
    changeName(event: any) {
      this.editing.name = event.target.value
    }
  }
}
</script>

<template>
  <ModalForm @save="store.save(editing.id, editing.name)" :id="editing.id" :name="editing.name"
             @change="changeName" text="newModule"/>
  <ModalConfirm @delete="store.delete(deleteId)"/>
  <ComponentToastSuccess message="saveSuccess"/>
  <ComponentToastError/>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="store.links" title="modules"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("modules") }}</h2>
        <button type="button" class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#addModal"
                @click="openModalSave"
        >
          {{ $t("add") }}
        </button>
      </div>
      <div class="card-body">
        <DefaultTable :modal="true" :data="store.modules" @edit="openModalEdit" @delete="openConfirmDeleteModal"/>
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