<script lang="ts">
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

export default {
  components: {ComponentSidebarInner},
  props: {
    inner: {
      type: Boolean,
      default: false
    },
    text: {
      type: Array as () => String[],
      required: false,
    }
  },
}
</script>

<template>
  <header :class="{inner: inner}">
    <div class="logo">
      <div class="a-wrapper">
        <div class="a">A</div>
      </div>
      <div class="group" :class="{'d-none': inner}">
        <div class="text-wrapper">ABC</div>
      </div>
    </div>
    <div v-if="inner" class="fw-bold d-flex align-items-md-center h-100">
      <div v-for="t in text" class="d-flex align-items-md-center justify-content-center fw-bold">
        <span class="header-separator">&nbsp</span>
        {{$t(t.toString())}}
      </div>
    </div>
  </header>
</template>

<style>
header {
  background-color: #ffffff;
  height: 10vh;
  border-bottom-style: solid;
  border-bottom-width: 1px;
  border-color: #00000033;
  display: flex;
  align-items: center;
  padding-left: 50px;
  width: 100vw;
  margin-bottom: 50px;
}

.inner {
  display: flex;
  width: 80vw !important;
  float: right;
  color: var(--primary-color);
  font-size: 16px;
  line-height: 16px;
  letter-spacing: 1px;
  text-transform: capitalize;
}


.header-separator {
  background-image: url("../assets/headerSeparator.svg");
  width: 30px;
  background-repeat: no-repeat;
  display: block;
}

.logo {
  display: flex;
  width: 30px;
  height: 25px;
}

.logo .a-wrapper {
  align-items: flex-start;
  background-color: var(--primary-color);
  border-radius: 2px;
  flex: 0 0 auto;
  flex-direction: column;
  gap: 6px;
  padding: 7px;
  position: relative;
}

.logo .a {
  color: #ffffff;
  font-family: "Poppins", Helvetica, serif;
  font-size: 14px;
  font-weight: 500;
  letter-spacing: 1px;
  line-height: 13px;
  margin-top: -0.79px;
  position: relative;
  white-space: nowrap;
  width: fit-content;
}

.logo .group {
  height: 24px;
  margin-right: -2px;
  position: relative;
  width: 37px;
}

.logo .text-wrapper {
  color: var(--primary-color);
  font-family: var(--bold-font-family);
  font-size: var(--bold-font-size);
  font-weight: var(--bold-font-weight);
  letter-spacing: var(--bold-letter-spacing);
  line-height: var(--bold-line-height);
  white-space: nowrap;
  padding-left: 20px;
}
</style>