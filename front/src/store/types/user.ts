export default interface User {
    id: string,
    name: string
    email: string,
    status: string,
    profile: string,
    dateCreated: Date,
    cpf: string,
    phone: string,
    admin: boolean,
    language: string,
    password: string
}