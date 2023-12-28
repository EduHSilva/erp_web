<script lang="ts">
  export default {
    props: {
      message: {
        type: String,
        required: true
      }
    }
  }
</script>

<template>
  <Teleport to="body">
    <div class="toast align-items-center text-bg-success border-0 position-fixed bottom-0 end-0 m-3" id="toast-success" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
          {{ $t(message) }}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  </Teleport>
</template>

<style scoped>

</style>