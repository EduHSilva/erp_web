<script setup lang="ts">

</script>

<template>
  <div id="component" class="container py-3 ">
    <div class="px-1 py-1 bg-white shadow  row align-items-center justify-content-center card">
      <div class="col-12 row align-items-center">
        <div class="position-relative col-1">
          <img
              class="radix-icons"
              alt="Radix icons"
              src="https://c.animaapp.com/pkHg1Qws/img/radix-icons-hamburger-menu.svg"
          />
        </div>
        <p class="text-black fs-5 fw-semibold font-family-Poppins col-10 m-0 px-3 py-2">
          Bem vindo Eduardo
        </p>
      </div>
      <div class="py-1 col-12 row">
        <div class="px-1 py-1 bg-light rounded-3 col-12 justify-content-center d-flex">
          <div class="col-9 row justify-content-center align-items-center">
            <div class="col-2 d-flex">
            </div>
            <div class="px-0.5 col-12 d-flex">
              <img class="img" alt="Fluent" src="../assets/icons/person.svg"/>
              <p class="text-black fs-6 fw-medium font-family-Poppins text-capitalize col-12 m-0 px-3 py-2">
                Users
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#component {
  position: relative;
  width: 300px;
}

img {
  width: 25px;
  height: auto;
}

p {
  padding: 10px 25px !important
}

</style>