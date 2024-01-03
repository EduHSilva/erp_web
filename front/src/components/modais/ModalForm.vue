<script lang="ts">
export default {
  props: {
    id: String,
    name: String,
    text: {
      type: String,
      required: true
    }
  },
  emits: ["change", "save"]
}
</script>

<template>
  <Teleport to="body">
    <form @submit.prevent="$emit('save')">
      <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="modalAddLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5" id="exampleModalLabel">{{ $t(text) }}</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <input type="hidden" class="form-control" :value="id">
              </div>
              <div class="mb-3">
                <label for="recipient-name" class="col-form-label">{{ $t("name") }}:</label>
                <input type="text" required name="name" class="form-control" :value="name"
                       @keyup="$emit('change', $event)">
              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-primary">
                {{ $t("save") }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </form>

  </Teleport>
</template>

<style scoped>

</style>