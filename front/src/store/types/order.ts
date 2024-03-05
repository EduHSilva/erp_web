import type ProductOrder from "@/store/types/productOrder";
import type Person from "@/store/types/person";

export default interface Order {
    id: string,
    statusOrder: string,
    total: number,
    client: Person,
    seller: Person,
    items: Array<ProductOrder>,
    addItems: Array<ProductOrder>,
    removeItems: Array<String>,
    dateCreated: Date,
    dueDate: Date,
    commission: number
}