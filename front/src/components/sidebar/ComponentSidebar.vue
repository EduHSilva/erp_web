<script lang="ts">
import ComponentSidebarLinks from "@/components/sidebar/ComponentSidebarLinks.vue";

export default {
  components: {ComponentSidebarLinks},
  data() {
    return {
      modules: [
        {
          img: "/icons/home.svg",
          description: "home",
          link: "/home"
        },
        {
          img: "/icons/admin.svg",
          description: "admin",
          link: "/admin"
        },
        {
          img: "/icons/logout.svg",
          description: "logout",
          link: "/logout"
        }
      ],
      username: ''
    }
  },
  created() {
    let name
    if (localStorage.getItem("username") != null) {
      name = localStorage.getItem("username")
    }
    if (name != undefined) this.username = name;
  },
}
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
          {{ $t("welcome") }}, {{username}}
        </p>
      </div>
      <ComponentSidebarLinks :links="modules"/>
    </div>
  </div>
</template>

<style scoped>
#component {
  position: relative;
  width: 300px;
}


p {
  padding: 10px 25px !important
}

</style>