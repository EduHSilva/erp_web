<script setup lang="ts">
import {RouterView} from 'vue-router'
import ComponentHeader from "@/components/ComponentHeader.vue";
</script>

<template>
  <ComponentHeader />
  <RouterView />
</template>

<style scoped>
  main {
    background-color: rgba(0,0,0, .1);
  }
</style>

