import {defineStore} from "pinia";
import type LinkSidebar from "@/store/types/linkSidebar";
import util from "../../../mixins/util"
import {axiosSalesInstance} from "../../config/axios.config";
import type Product from "@/store/types/product";

interface State {
    productList: Product[],
    totalPages: 0,
    page: 0,
    links: LinkSidebar[],
}

export const useProductStore = defineStore('product', {
    state: (): State => {
        return {
            productList: [],
            totalPages: 0,
            page: 0,
            links: [
                {
                    img: "/icons/list.svg",
                    description: "list",
                    link: "/sales/products"
                },
                {
                    img: "/icons/add.svg",
                    description: "add",
                    link: "/sales/product"
                }
            ],
        }
    },
    actions: {
        async get(index: number) {
            try {
                let response = await axiosSalesInstance(`sales/products?page=${index}&sort=dateCreated,asc`)
                this.productList = response.data.content
                this.totalPages = response.data.totalPages
                this.page = response.data.number
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axiosSalesInstance.delete(`sales/product/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                let response = await axiosSalesInstance(`sales/product/${id}`)
                return response.data
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async save(product: Product) {
            try {
                if (product.id != undefined && product.id.trim() != "") {
                    await axiosSalesInstance.put(`sales/product/${product.id}`, product)
                } else {
                    product.status = "ACTIVE";
                    await axiosSalesInstance.post(`sales/products`, product)
                }
                util.methods.showToastSuccess(() => window.location.href = "/sales/products");
            } catch (e) {
                util.methods.showToastError();
            }
        },

    },

})

