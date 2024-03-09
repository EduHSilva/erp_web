<script lang="ts" setup>
import ComponentHome from "@/components/ComponentHome.vue";
</script>

<template>
  <ComponentHome />
</template>


<style scoped>
hr {
  stroke-width: 1px;
  stroke: #E8E8E8;
}

button {
  border-radius: 8px;
  background: var(--primary-color);
  color: #fff;
  font-weight: var(--bold-font-weight);
  box-shadow: 0 1px 1px 0 var(--primary-color);
  float: right;
  font-feature-settings: 'clig' off, 'liga' off;
  display: flex;
  padding: 8px 12px;
  align-items: center;
}


</style>