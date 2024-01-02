<script lang="ts">
export default {
  props: {
    modal: true,
    data: {
      id: "",
      name: ""
    }
  }
}
</script>

<template>
  <td>
    <div class="dropdown">
      <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
              aria-expanded="false">
        {{ $t("actions") }}
      </button>
      <ul class="dropdown-menu">
        <li>
          <button class="dropdown-item" v-if="modal" data-bs-toggle="modal"
                  data-bs-target="#addModal" @click="$emit('edit', data.id, data.name)">
            {{ $t("edit") }}
          </button>
          <button class="dropdown-item" v-else @click="$emit('edit', data.id, data.name)">
            {{ $t("edit") }}
          </button>
        </li>
        <li>
          <button class="dropdown-item danger" @click="$emit('delete', data.id)" data-bs-toggle="modal"
                  data-bs-target="#confirmModal">{{ $t("delete") }}
          </button>
        </li>
      </ul>
    </div>
  </td>
</template>

<style scoped>

</style>