<script lang="ts">
import ComponentSidebarLinks from "@/components/sidebar/ComponentSidebarLinks.vue";
import * as Module from "module";

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

    let modules = localStorage.getItem("modules");
    if (modules != null) {
      let modulesArray = JSON.parse(modules)
      if (this.isNameInArray('Admin', modulesArray)) {
        this.modules.push(
            {
              img: "/icons/admin.svg",
              description: "admin",
              link: "/admin"
            },
        )
      }
      if (this.isNameInArray('Vendas', modulesArray)) {
        this.modules.push(
            {
              img: "/icons/sales.svg",
              description: "sales",
              link: "/sales"
            },
        )
      }
      if (this.isNameInArray('Compras', modulesArray)) {
        this.modules.push(
            {
              img: "/icons/shop.svg",
              description: "shop",
              link: "/shop"
            },
        )
      }
    }
    this.modules.push({
      img: "/icons/logout.svg",
      description: "logout",
      link: "/logout"
    })
  },
  methods: {
    isNameInArray(nameToCheck: string, array: Array<Module>) {
      for (let i = 0; i < array.length; i++) {
        if (array[i].name.toLowerCase() === nameToCheck.toLowerCase()) {
          return true;
        }
      }
      return false;
    }
  }
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
          {{ $t("welcome") }}, {{ username }}
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