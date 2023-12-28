<script lang="ts">
export default {
  emits: ["delete"]
}
</script>

<template>
  <Teleport to="body">
    <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">{{ $t("attention") }}</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            {{$t("confirmDelete")}}
          </div>
          <div class="modal-footer">
            <button type="button" data-bs-dismiss="modal" class="btn btn-primary">
              {{ $t("cancel") }}
            </button>
            <button type="button" @click="$emit('delete')" data-bs-dismiss="modal" class="btn btn-danger">
              {{ $t("confirm") }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </Teleport>
</template>

<style scoped>

</style>