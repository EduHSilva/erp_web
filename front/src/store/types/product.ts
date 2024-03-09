export default interface Product {
    id: string,
    name: string
    description: string
    img_url: string,
    price: number,
    stock: number,
    status: string
    dateCreated: Date
}