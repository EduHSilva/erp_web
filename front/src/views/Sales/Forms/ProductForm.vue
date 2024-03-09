<script lang="ts">
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import { useProductStore } from "@/store/modules/sales/productModule";
import type LinkSidebar from "@/store/types/linkSidebar";
import type Product from "@/store/types/product";

export default {
  async created() {
    let id: string = this.$route.params.id as string
    if (id != null && id.trim() != "") {
      this.productOriginal = await useProductStore().getOne(id)
      this.product = JSON.parse(JSON.stringify(this.productOriginal)) as Product;
      this.text.push("edit")
      this.edit = true
    } else {
      this.product.dateCreated = new Date()
      this.text.push("add")
    }

    this.links = useProductStore().links
  },
  components: {ComponentSidebarInner, ComponentHeader, ComponentToastSuccess, ComponentToastError},
  data() {
    return {
      text: ["sales", "products"],
      productOriginal: {} as Product,
      product: {} as Product,
      edit: false,
      links: Array<LinkSidebar>(),
    }
  },
  methods: {
    saveProduct() {
      useProductStore().save(this.product)
    },
  }
}
</script>

<template>
  <ComponentToastSuccess message="success"/>
  <ComponentToastError/>

  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="links" title="orders"/>
    </div>
    <div class="col-8 card ">
      <form @submit.prevent="saveProduct">
        <div class="card-header">
          <h2 class="text-center py-2">
            {{ $t(text[text.length - 1]) }}
          </h2>
        </div>
        <div class="card-body d-flex">
          <div class="col">
            <div class="row">
              <div class="col">
                <img v-if="product.img_url" :src="product.img_url"
                     alt="Imagem do Produto" class="mt-2 img-fluid">
                <img v-else src="https://multilit.com.br/wp-content/uploads/2020/03/Produto-sem-foto.png"
                     alt="Imagem do Produto" class="mt-2 img-fluid">
              </div>
              <div class="col">
                <div class="mb-3">
                  <label for="imagemProduto" class="form-label">{{ $t("image") }}</label>
                  <input type="text" name="url" class="form-control" v-model="product.img_url">
                </div>
                <div class="mb-3">
                  <label for="name" class="form-label">{{ $t("name") }}</label>
                  <input type="text" name="name" class="form-control" v-model="product.name">
                </div>
                <div class="mb-3">
                  <label for="status" class="form-label"> {{ $t("status") }} </label>
                  <select name="status" id="status" v-model="product.status" class="form-select">
                    <option value="ACTIVE" :selected="product.status == 'ACTIVE'">{{ $t("active") }}</option>
                    <option value="INACTIVE" :selected="product.status == 'INACTIVE'">{{ $t("inactive") }}</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="price" class="form-label">{{ $t("price") }}</label>
                  <input type="number" name="price" class="form-control" v-model="product.price" required>
                </div>
                <div class="mb-3">
                  <label for="stock" class="form-label">{{ $t("stock") }}</label>
                  <input type="number" class="form-control" name="stock" v-model="product.stock" required>
                </div>
                <div class="mb-3">
                  <label for="description" class="form-label">{{ $t("description") }}</label>
                  <textarea class="form-control" name="description" v-model="product.description" required></textarea>
                </div>
              </div>
            </div>


            <button type="submit" class="btn btn-primary">{{ $t("save") }}</button>
          </div>
        </div>
      </form>

    </div>
  </main>
</template>

<style scoped>
img {
  width: auto;
  max-height: 500px;
  border-radius: 8px;
}
</style>