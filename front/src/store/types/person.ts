import type City from "@/store/types/city";

export default interface Person {
    id: string,
    name: string
    email: string,
    status: string,
    dateCreated: Date,
    cpf_cnpj: string,
    type: string,
    phone: string,
    city: City
}