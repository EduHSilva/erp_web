<script lang="ts" setup>
import ComponentTableUsers from "@/components/ComponentTableUsers.vue";
import ComponentSidebar from "@/components/ComponentSidebar.vue";

import {useUserStore} from "@/store/modules/userModule";
import ComponentPagination from "@/components/ComponentPagination.vue";

const store = useUserStore()
store.getUsers()
</script>

<template>
  <main>
    <div class="col-3">
      <ComponentSidebar />
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">User Information</h2>
      </div>
      <div class="card-body">
        <ComponentTableUsers :users="store.userList"  />
      </div>
      <div class="card-footer">
        <ComponentPagination />
      </div>
    </div>
  </main>
</template>


<style scoped>
main {
  display: flex;
  padding: 15px;
  min-height: 90vh;
}

hr {
  stroke-width: 1px;
  stroke: #E8E8E8;
}


</style>