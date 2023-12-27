<script lang="ts" setup>
import ComponentSidebar from "@/components/sidebar/ComponentSidebar.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
</script>

<template>
  <ComponentHeader/>
  <main>
    <div class="col-3">
      <ComponentSidebar/>
    </div>
    <div class="col-8">
      <div class="card">
        <h2 class="p-lg-5"> {{ $t("administration") }} </h2>

        <div class="card-body d-flex justify-content-around">

          <router-link to="/admin/modules" class="card-item bg-light">
            <div class="card-inner">
              <h5 class="card-title"> {{ $t("modules") }}</h5>
            </div>
          </router-link>

          <div class="card-item bg-light">
            <div class="card-inner">
              <h5 class="card-title"> {{ $t("profileAccess") }}</h5>
            </div>
          </div>

          <router-link to="/admin/users" class="card-item bg-light">
            <div class="card-inner">
              <h5 class="card-title"> {{ $t("users") }}</h5>
            </div>
          </router-link>
        </div>
      </div>
    </div>


  </main>
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
