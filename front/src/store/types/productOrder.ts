import type Product from "@/store/types/product";

export default interface ProductOrder {
    productID: string,
    product: Product,
    orderID: string,
    priceUnit: number,
    quantity: number,
}