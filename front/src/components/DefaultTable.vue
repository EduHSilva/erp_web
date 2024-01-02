<script lang="ts">
import util from '@/mixins/util'
import type Module from "@/store/types/module";

export default {
  props: {
    data: {
      type: Array as () => Module[],
      required: true
    },
    modal: {
      type: Boolean,
      required: true
    }
  },
  mixins: [util]
}
</script>

<template>
  <div class="container">
    <div class="table-responsive" v-if="data.length != 0">
      <table class="table">
        <thead class="table-header">
        <tr>
          <th scope="col">{{ $t("name") }}</th>
          <th scope="col">{{ $t("dateCreated") }}</th>
          <th scope="col">{{ $t("actions") }}</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="d in data">
          <td>{{ d.name }}</td>
          <td>{{ formatDate(d.dateCreated) }}</td>
          <td>
            <div class="dropdown">
              <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                      aria-expanded="false">
                {{ $t("actions") }}
              </button>
              <ul class="dropdown-menu">
                <li>
                  <button class="dropdown-item" v-if="modal" data-bs-toggle="modal"
                          data-bs-target="#addModal" @click="$emit('edit', d.id, d.name)">
                    {{ $t("edit") }}
                  </button>
                  <button class="dropdown-item" v-else @click="$emit('edit', d.id, d.name)">
                    {{ $t("edit") }}
                  </button>
                </li>
                <li>
                  <button class="dropdown-item danger" @click="$emit('delete', d.id)" data-bs-toggle="modal"
                          data-bs-target="#confirmModal">{{ $t("delete") }}
                  </button>
                </li>
              </ul>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <div class="w-100 d-flex justify-content-center fw-bold">
        {{ $t("noData") }}
      </div>
    </div>
  </div>
</template>

<style scoped>
th {
  font-weight: bold;
}
</style>