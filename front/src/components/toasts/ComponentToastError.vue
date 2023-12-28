<script lang="ts">
export default {}
</script>

<template>
  <Teleport to="body">
    <div class="toast align-items-center text-bg-danger border-0 position-fixed bottom-0 end-0 m-3" id="toast-error" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
          {{ $t("error") }}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  </Teleport>
</template>

<style scoped>

</style>