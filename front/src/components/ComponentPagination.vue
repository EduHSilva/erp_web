<script lang="ts">
export default {
  props: {
    totalPages: {
      type: Number,
      required: true
    },
    page: {
      type: Number,
      required: true
    }
  }
}
</script>

<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-end">
      <li class="page-item">
        <a class="page-link" aria-label="Previous" @click="$emit('change-pagination', 0)">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item" v-for="index in totalPages" :key="index" :class="{ active: page == index - 1 }">
        <a class="page-link" @click="$emit('change-pagination', index - 1)"
           href="#">{{ index }}</a>
      </li>
      <li class="page-item">
        <a class="page-link" @click="$emit('change-pagination', totalPages - 1)" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<style scoped>
.pagination .page-item.active a {
  background-color: var(--primary-color);
  border-color: var(--primary-color);
  color: #fff;
}

.pagination .page-item a {
  color: var(--primary-color);
}

.pagination .page-item a:hover {
  background-color: var(--secondary-color);
  color: var(--primary-color);
}


.pagination .page-item a:focus {
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.06);
}

.pagination .page-item a {
  cursor: pointer;
}

.pagination .page-item a {
  transition: background-color 0.3s, color 0.3s;
}

</style>