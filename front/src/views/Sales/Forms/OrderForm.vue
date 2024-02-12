<script lang="ts">
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import type Product from "@/store/types/product";
import type LinkSidebar from "@/store/types/linkSidebar";
import {useProductStore} from "@/store/modules/sales/productModule";
import {useOrderStore} from "@/store/modules/sales/ordersModule";
import type Order from "@/store/types/order";
import {usePersonStore} from "@/store/modules/sales/personModule";
import type Person from "@/store/types/person";
import type ProductOrder from "@/store/types/productOrder";
import util from "@/mixins/util";

export default {
  async created() {
    let id: string = this.$route.params.id as string
    if (id != null && id.trim() != "") {
      this.orderOriginal = await useOrderStore().getOne(id)
      this.order = JSON.parse(JSON.stringify(this.orderOriginal)) as Order;
      this.text.push("edit")
      this.edit = true
      this.sellerSearch = this.order.seller.name
      this.clientSearch = this.order.client.name
      this.products = this.order.items
    } else {
      this.order.dateCreated = new Date()
      this.order.items = []
      this.order.total = 0
      this.text.push("add")
    }

    this.links = useOrderStore().links
  },
  components: {ComponentSidebarInner, ComponentHeader, ComponentToastSuccess, ComponentToastError},
  data() {
    return {
      text: ["sales", "orders"],
      orderOriginal: {} as Order,
      order: {} as Order,
      edit: false,
      links: Array<LinkSidebar>(),
      clients: Array<Person>,
      sellers: Array<Person>,
      products: Array<Product>,
      clientSearch: "",
      productsSearch: "",
      sellerSearch: "",
      quantity: 0
    }
  },
  methods: {
    saveOrder() {
      useOrderStore().save(this.order)
    },
    async getClients() {
      await usePersonStore().get(-1)
      this.clients = usePersonStore().clientList
      this.getClient()
    },
    getClient() {
      const client: Person | undefined = this.clients.find((client: Person) => client.name === this.clientSearch);
      if (client !== undefined) {
        this.order.client = client;
      }
    },
    async getSellers() {
      await usePersonStore().getSellers(-1)
      this.sellers = usePersonStore().sellersList
      this.getSeller()
    },
    getSeller() {
      const seller: Person | undefined = this.sellers.find((seller: Person) => seller.name === this.sellerSearch);
      if (seller !== undefined) {
        this.order.seller = seller;
      }
    },
    async getProducts() {
      await useProductStore().get(-1)
      this.products = useProductStore().productList
    },
    linkProduct() {
      const product: Product | undefined = this.products.find((product: Product) => product.name === this.productsSearch);
      if (product) {
        let productOrder = {} as ProductOrder
        productOrder.productID = product.id
        productOrder.product = product;
        productOrder.priceUnit = product.price
        productOrder.quantity = this.quantity
        if (this.edit) {
          productOrder.productID = this.orderOriginal.id
        }

        this.order.items.push(productOrder);
        this.updateTotal()
      }
    },
    updateTotal() {
      let total = 0;
      this.order.items.forEach(e => {
        total += e.quantity * e.priceUnit
      })
      this.order.total = total
    }
  },
  mixins: [util]
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
      <form @submit.prevent="saveOrder">
        <div class="card-header">
          <h2 class="text-center py-2">
            {{ $t(text[text.length - 1]) }}
          </h2>
        </div>
        <div class="card-body d-flex">
          <div class="col p-3">
            <div class="mb-3">
              <label for="client" class="form-label">{{ $t("client") }}</label>
              <input class="form-control" list="datalistOptions" v-model="clientSearch" @keydown="getClients"
                     placeholder="Type to search...">
              <datalist id="datalistOptions">
                <option v-for="client in clients" :value="client.name" :data-value="client.id"/>
              </datalist>
            </div>
            <div class="mb-3">
              <label for="sellers" class="form-label">{{ $t("seller") }}</label>
              <input class="form-control" list="dataSellers" v-model="sellerSearch" @keydown="getSellers"
                     placeholder="Type to search...">
              <datalist id="dataSellers">
                <option v-for="seller in sellers" :value="seller.name" :data-value="seller.id"/>
              </datalist>
            </div>
            <div class="mb-3">
              <label for="date" class="form-label">{{ $t("commission") }}</label>
              <input type="text" class="form-control" v-model="order.commission">
            </div>
            <div class="mb-3">
              <label for="date" class="form-label">{{ $t("dateCreated") }}</label>
              <input type="text" disabled class="form-control" :value="formatDate(order.dateCreated)">
            </div>
            <div class="mb-3">
              <label for="dueDate" class="form-label">{{ $t("dueDate") }}</label>
              <input type="text" class="form-control" v-model="order.dueDate">
            </div>
          </div>
          <div class="col p-3">
            <div class="mb-3 d-flex flex-column">
              <div class="mb-3">
                <label for="products" class="form-label">{{ $t("products") }}</label>
                <input class="form-control" list="datalistProduct" v-model="productsSearch" @keydown="getProducts"
                       placeholder="Type to search...">
                <datalist id="datalistProduct">
                  <option v-for="product in products" :value="product.name" :data-value="product.id"/>
                </datalist>
              </div>
              <div class="mb-3">
                <label for="quantity" class="form-label">{{ $t("quantity") }}</label>
                <input type="number" class="form-control" v-model="quantity" name="quantity">
              </div>
              <div class="mb-1">
                <button class="btn btn-primary" @click.prevent="linkProduct">{{ $t("add") }}</button>
              </div>
            </div>

            <hr>
            <h4>{{ $t("products") }}</h4>
            <div class="d-flex justify-content-sm-between col-12">
              <div class="col-6">
                <span class="fw-bold"> {{ $t("name") }}</span>
              </div>
              <div class="col-2">
                <span class="fw-bold"> {{ $t("price") }}</span>
              </div>
              <div class="col-2">
                <span class="fw-bold"> {{ $t("qtd") }}</span>
              </div>
              <div class="col-2">
                <span class="fw-bold"> {{ $t("subtotal") }}</span>
              </div>
            </div>
            <div v-for="product in order.items" class="d-flex justify-content-sm-between col-12">
              <div class="col-6">
                <span> {{ product.product.name }}</span>
              </div>
              <div class="col-2">
                <span> {{ product.product.price }}</span>
              </div>
              <div class="col-2">
                <span> {{ product.quantity }}</span>
              </div>
              <div class="col-2">
                <span> {{ product.priceUnit * product.quantity }}</span>
              </div>
            </div>
            <hr>
            <div class="mb-3 d-flex justify-content-end">
              <span class="fw-bold">{{ $t("total") }}: {{ order.total }}</span>
            </div>
            <button type="submit" class="btn btn-primary">{{ $t("save") }}</button>
          </div>
        </div>
      </form>

    </div>
  </main>
</template>

<style scoped>
</style>