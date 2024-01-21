import type Product from "@/store/types/product";

export default interface ProductOrder {
    product: Product,
    price_unit: number,
    quantity: number,
    subtotal: number
}