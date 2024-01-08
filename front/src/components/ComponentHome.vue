<script lang="ts">
import {defineComponent} from 'vue'
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebar from "@/components/sidebar/ComponentSidebar.vue";

export default defineComponent({
  name: "ComponentHome",
  components: {ComponentSidebar, ComponentHeader}
})
</script>

<template>
  <ComponentHeader />
  <main>
    <div class="col-3">
      <ComponentSidebar />
    </div>
    <div class="col-8">
      <slot></slot>
    </div>
  </main>
</template>

<style scoped>

</style>