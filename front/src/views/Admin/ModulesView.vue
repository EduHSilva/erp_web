<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

import ComponentPagination from "@/components/ComponentPagination.vue";
import ComponentTable from "@/components/tables/ComponentTable.vue";
import { useModulesStore } from "@/store/modules/admin/modulesModule";

const store = useModulesStore()
store.get(0)
</script>
<script lang="ts">
import util from "@/mixins/util";

export default {
  data() {
    return {
      text: ["admin", "modules", "list"],
      editing: {
        id: "",
        name: ""
      },
      deleteId: '',
      tableHeader: ["name", "dateCreated"]
    }
  },
  methods: {
    openModalSave() {
      this.editing = {
        id: "",
        name: ""
      }
    },
    changeName(event: any) {
      this.editing.name = event.target.value
    }
  },
  mixins: [util]
}
</script>

<template>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="store.links" title="modules"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("modules") }}</h2>
      </div>
      <div class="card-body">
        <ComponentTable :table-header="tableHeader" :table-data="store.modules">
          <tr v-for="module in store.modules" :key="module.id">
            <td>{{ module.name }}</td>
            <td>{{ $d(Date.parse(module.dateCreated.toString())) }}</td>
          </tr>
        </ComponentTable>
      </div>
      <div class="card-footer">
        <ComponentPagination @changePagination="store.get" :total-pages="store.totalPages" :page="store.page" back="admin"/>
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