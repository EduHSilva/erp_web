<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

import ComponentPagination from "@/components/ComponentPagination.vue";
import {useModulesStore} from "@/store/modules/modulesModule";

const store = useModulesStore()
store.getModules()
</script>
<script lang="ts">
export default {
  data() {
    return {
      links: [
        {
          img: "/icons/list.svg",
          description: "list",
          link: "/admin/modules"
        },
      ],
      text: ["admin", "modules", "list"],
      nome: '',
      showModal: true
    }
  },
  methods: {
    save() {
      console.log('Nome salvo:', this.nome);
    },
  }
}
</script>

<template>
  <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">New message</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="recipient-name" class="col-form-label">Recipient:</label>
              <input type="text" class="form-control" id="recipient-name">
            </div>
            <div class="mb-3">
              <label for="message-text" class="col-form-label">Message:</label>
              <textarea class="form-control" id="message-text"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Send message</button>
        </div>
      </div>
    </div>
  </div>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="links" title="modules"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("modules") }}</h2>
        <button type="button" class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#addModal">
          {{$t("add")}}
        </button>

      </div>
      <div class="card-body">
        <!--        <DefaulTable :data="store.modules"  />-->
      </div>
      <div class="card-footer">
        <ComponentPagination/>
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