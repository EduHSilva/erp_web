import type Module from "@/store/types/module";

export default interface Profile {
    id: string,
    name: string,
    dateCreated: Date
    adminModules: Module[]
}