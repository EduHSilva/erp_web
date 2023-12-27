<script lang="ts" setup>
import ComponentTableUsers from "@/components/ComponentTableUsers.vue";
import ComponentSidebar from "@/components/sidebar/ComponentSidebar.vue";

import {useUserStore} from "@/store/modules/userModule";
import ComponentPagination from "@/components/ComponentPagination.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

const store = useUserStore()
store.getUsers()
</script>

<script lang="ts">
export default  {
  data() {
      return {
        text: ["admin", "users", "list"],
        links: [
          {
            img: "/icons/list.svg",
            description: "list",
            link: "/admin/users"
          },
          {
            img: "/icons/add.svg",
            description: "add",
            link: "/admin/users/edit"
          }
        ]
      }
  },
}
</script>

<template>
  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner  :links="links" title="users"/>
    </div>
    <div class="col-8 card ">
      <div class="card-header">
        <h2 class="text-center py-2">{{ $t("userInfo")}}</h2>
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