import type ProductOrder from "@/store/types/productOrder";
import type Person from "@/store/types/person";

export default interface Order {
    id: string,
    status: string,
    total: number,
    client: Person,
    seller: Person,
    products: Array<ProductOrder>
    dateCreated: Date,
    dueDate: Date
}