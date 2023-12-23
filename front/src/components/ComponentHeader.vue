<script setup lang="ts">

</script>

<template>
  <header>
    <div class="ABC-logo">
      <div class="a-wrapper">
        <div class="a">A</div>
      </div>
      <div class="group">
        <div class="text-wrapper">ABC</div>
      </div>
    </div>
  </header>
</template>

<style scoped>
header {
  background-color: #ffffff;
  border-bottom-style: solid;
  border-bottom-width: 0.5px;
  border-color: #00000033;
  height: 10vh;
}

header .ABC-logo {
  align-items: center;
  display: inline-flex;
  gap: 8px;
  left: 56px;
  position: relative;
  top: 35px;
}

header .a-wrapper {
  align-items: flex-start;
  background-color: #1d0202;
  border-radius: 1.58px;
  display: inline-flex;
  flex: 0 0 auto;
  flex-direction: column;
  gap: 6.3px;
  padding: 7.88px;
  position: relative;
}

header .a {
  color: #ffffff;
  font-family: "Poppins", Helvetica,serif;
  font-size: 14.2px;
  font-weight: 500;
  letter-spacing: 0.57px;
  line-height: 12.6px;
  margin-top: -0.79px;
  position: relative;
  white-space: nowrap;
  width: fit-content;
}

header .group {
  height: 24px;
  margin-right: -2px;
  position: relative;
  width: 37px;
}

header .text-wrapper {
  color: var(--lightgrayscale-100);
  font-family: var(--body-1-bold-font-family);
  font-size: var(--body-1-bold-font-size);
  font-style: var(--body-1-bold-font-style);
  font-weight: var(--body-1-bold-font-weight);
  left: 0;
  letter-spacing: var(--body-1-bold-letter-spacing);
  line-height: var(--body-1-bold-line-height);
  position: absolute;
  top: 0;
  white-space: nowrap;
}
</style>