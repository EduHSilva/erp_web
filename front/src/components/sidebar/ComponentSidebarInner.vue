<script lang="ts">
import type LinkSidebar from "@/store/types/linkSidebar";
import ComponentSidebarLinks from "@/components/sidebar/ComponentSidebarLinks.vue";

export default {
  components: {ComponentSidebarLinks},
  props: {
    links: {
      type: Array as () => LinkSidebar[],
      required: true,
    },
    title: {
      type: String,
      required: true
    }
  },
}
</script>
<template>
  <div class="nav-bar rounded">
    <div class="container">
      <a class="logo" href="/">
        <div class="a-wrapper">
          <div class="a">A</div>
        </div>
        <div class="group">
          <div class="text-wrapper">ABC</div>
        </div>
      </a>
      <div class="dashboard-mainmenu">
        <div class="frame">
          <h4>{{ $t(title) }}</h4>
        </div>
        <ComponentSidebarLinks :links="links"/>
      </div>
    </div>
  </div>
</template>

<style>
.nav-bar {
  box-shadow: 0 1px 3px #00000033;
  height: 75vh;
  width: 20vw;
  padding: 20px 10px;
  position: absolute;
  top: 0;
  left: 0;
  background: #fff;
}

.container {
  display: flex;
  flex-direction: column;
  min-height: 200px;
  justify-content: space-between;
}


.nav-bar .text-wrapper {
  color: var(--primary-color);
  font-family: var(--bold-font-family);
  font-size: var(--bold-font-size);
  font-weight: var(--bold-font-weight);
  letter-spacing: var(--bold-letter-spacing);
  line-height: var(--bold-line-height);
  white-space: nowrap;
  padding: 0 5px;
}

.frame {
  width: 100%;
}
</style>
