<script lang="ts" setup>
import ComponentSidebar from "@/components/sidebar/ComponentSidebar.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentHome from "@/components/ComponentHome.vue";
</script>

<template>
  <ComponentHome>
    <div class="card">
      <h2 class="p-lg-5"> {{ $t("sales") }} </h2>

      <div class="card-body d-flex justify-content-around">
        <router-link to="/sales/clients" class="card-item bg-light">
          <div class="card-inner">
            <h5 class="card-title"> {{ $t("clients") }}</h5>
          </div>
        </router-link>

        <router-link to="/sales/sellers" class="card-item bg-light">
          <div class="card-inner">
            <h5 class="card-title"> {{ $t("sellers") }}</h5>
          </div>
        </router-link>

        <router-link to="/sales/products" class="card-item bg-light">
          <div class="card-inner">
            <h5 class="card-title"> {{ $t("products") }}</h5>
          </div>
        </router-link>

        <router-link to="/sales/orders" class="card-item bg-light">
          <div class="card-inner">
            <h5 class="card-title"> {{ $t("orders") }}</h5>
          </div>
        </router-link>
      </div>
    </div>
  </ComponentHome>
</template>

<style scoped>
.card {
  width: 100%;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
}

.card-body {
  padding: 20px;
}

.card-item {
  width: 100%;
  max-width: 250px;
  margin: 0 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s;
  height: 132px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card-item:hover {
  transform: scale(1.05);
}

.card-inner {
  padding: 20px;
}

.card-title {
  margin: 0;
  font-size: 18px;
  color: #333;
}
</style>
