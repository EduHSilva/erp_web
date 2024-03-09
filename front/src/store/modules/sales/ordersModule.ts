import type LinkSidebar from "@/store/types/linkSidebar";
import type Order from "@/store/types/order";
import type Product from "@/store/types/product";
import { defineStore } from "pinia";
import util from "../../../mixins/util";
import { axiosSalesInstance } from "../../config/axios.config";

interface State {
    productList: Product[],
    ordersList: Order[],
    totalPages: 0,
    page: 0,
    links: LinkSidebar[],
}

export const useOrderStore = defineStore('order', {
    state: (): State => {
        return {
            productList: [],
            ordersList: [],
            totalPages: 0,
            page: 0,
            links: [
                {
                    img: "/icons/list.svg",
                    description: "list",
                    link: "/sales/orders"
                },
                {
                    img: "/icons/add.svg",
                    description: "add",
                    link: "/sales/order"
                }
            ],
        }
    },
    actions: {
        async get(index: number) {
            try {
                const response = await axiosSalesInstance(`sales/orders?page=${index}&sort=dateCreated,asc`)
                this.ordersList = response.data.content
                this.totalPages = response.data.totalPages
                this.page = response.data.number
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async delete(id: string) {
            try {
                await axiosSalesInstance.delete(`sales/order/${id}`)

                util.methods.showToastSuccess(null);
                await this.get(0);
            } catch (e) {
                util.methods.showToastError();
            }
        },
        async getOne(id: string) {
            try {
                const response = await axiosSalesInstance(`sales/order/${id}`)
                return response.data
            } catch (ex) {
                util.methods.showToastError();
            }
        },
        async save(order: Order) {
            try {
                if (order.id != undefined && order.id.trim() != "") {
                    await axiosSalesInstance.put(`sales/order/${order.id}`, order)
                } else {
                    order.statusOrder = "PENDENT";
                    await axiosSalesInstance.post(`sales/orders`, order)
                }
                util.methods.showToastSuccess(() => window.location.href = "/sales/orders");
            } catch (e) {
                util.methods.showToastError();
            }
        },

    },

})

