import type Profile from "@/store/types/profile";

export default interface User {
    id: string,
    name: string
    email: string,
    status: string,
    profile: Profile,
    dateCreated: Date,
    cpf: string,
    phone: string,
    admin: boolean,
    seller: boolean,
    language: string,
    password: string
}