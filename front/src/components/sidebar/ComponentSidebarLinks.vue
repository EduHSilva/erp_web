<script lang="ts">
  import type LinkSidebar from "@/store/types/linkSidebar";

  export default {
    props: {
      links: {
        type: Array as () => LinkSidebar[],
        required: true,
      }
    },
  }
</script>

<template>
  <div class="py-2 col-12 row">
    <router-link v-for="link, i in links" :to="link.link" :key="i" class="px-1 py-1 rounded-3 col-12 justify-content-center d-flex">
      <div class="col-9 row justify-content-center align-items-center">
        <div class="col-2 d-flex">
        </div>
        <div class="px-0.5 col-12 d-flex">
          <img class="img" alt="Fluent" :src="link.img"/>
          <p class="text-black fs-6 fw-medium font-family-Poppins text-capitalize col-12 m-0 px-3 py-2">
            {{ $t(link.description) }}
          </p>
        </div>
      </div>
    </router-link>
  </div>
</template>

<style scoped>
img {
  width: 25px;
  height: auto;
}
</style>