<script lang="ts">
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import {useProfileStore} from "@/store/modules/admin/profilesModule";
import util from "@/mixins/util";
import type Profile from "@/store/types/profile";
import type LinkSidebar from "@/store/types/linkSidebar";
import {useUserStore} from "@/store/modules/admin/userModule";
import type User from "@/store/types/user";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import {usePersonStore} from "@/store/modules/sales/personModule";
import type Person from "@/store/types/person";
import type City from "@/store/types/city";

export default {
  components: {ComponentToastError, ComponentToastSuccess, ComponentHeader, ComponentSidebarInner},
  async created() {
    let id: string = this.$route.params.id as string
    if (id != null && id.trim() != "") {
      this.personOriginal = await usePersonStore().getOne(id)
      this.person = JSON.parse(JSON.stringify(this.personOriginal)) as Person;
      this.citySearch = this.person.city.name
      this.text.push("edit")
      this.edit = true
    } else {
      this.person.dateCreated = new Date()
      this.text.push("add")
    }

    this.links = usePersonStore().links
    await usePersonStore().getCities(this.citySearch)
    this.cities = usePersonStore().cities
  },
  mixins: [util],
  data() {
    return {
      text: ["sales", "users"],
      personOriginal: {} as Person,
      person: {} as Person,
      edit: false,
      cities: [{
        name: "",
        id: "",
        state: {
          name: "",
          id: "",
          acronym: ""
        }
      }],
      links: Array<LinkSidebar>(),
      citySearch: ""
    }
  },
  methods: {
    async save() {
      if (this.isEqual(this.personOriginal, this.person)) {
        this.$router.push({path: '/sales/persons/clients'})
      } else {
        await usePersonStore().save(this.person)
      }
    },
    isEqual(userA: Person, userB: Person) {
      return JSON.stringify(userA) === JSON.stringify(userB);
    },
    async getCities() {
      await usePersonStore().getCities(this.citySearch)
      this.cities = usePersonStore().cities
      this.getCity()
    },
    getCity() {
      const city = this.cities.find(city => city.name === this.citySearch);
      if (city != undefined) {
        this.person.city = city;
      }
    }
  }
}
</script>

<template>
  <ComponentToastSuccess message="success"/>
  <ComponentToastError/>

  <ComponentHeader inner :text="text"/>
  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="links" title="clients"/>
    </div>
    <div class="col-8 card ">
      <form @submit.prevent="save">
        <div class="card-header">
          <h2 class="text-center py-2">
            {{ $t(text[text.length - 1]) }}
          </h2>
        </div>
        <div class="card-body d-flex">
          <div class="col">
            <div class="mb-3">
              <label for="name" class="form-label"> {{ $t("name") }} </label>
              <input class="form-control input" id="name" name="name" :placeholder="$t('name')"
                     v-model="person.name" required>
            </div>
            <div class="mb-3">
              <label for="email" class="form-label"> {{ $t("email") }} </label>
              <input class="form-control input" name="email" :placeholder="$t('email')"
                     v-model="person.email" required type="email">
            </div>
            <div class="mb-3">
              <label for="cpf" class="form-label"> {{ $t("type") }} </label>
              <select name="type" id="type" class="form-control input" v-model="person.type">
                <option value="CLIENT_PF" selected>{{ $t("pf") }}</option>
                <option value="CLIENT_PJ">{{ $t("pj") }}</option>
              </select>
            </div>
            <div class="mb-3" v-if="person.type == 'CLIENT_PF'">
              <label for="cpf" class="form-label"> {{ $t("cpf") }} </label>
              <input class="form-control input" name="cpf" :placeholder="$t('cpf')"
                     v-model="person.cpf_cnpj" required>
            </div>
            <div class="mb-3" v-else>
              <label for="cpf" class="form-label"> {{ $t("cnpj") }} </label>
              <input class="form-control input" name="cpf" :placeholder="$t('cnpj')"
                     v-model="person.cpf_cnpj" required>
            </div>
            <div class="mb-3">
              <label for="name" class="form-label"> {{ $t("phone") }} </label>
              <input class="form-control input" name="phone" :placeholder="$t('phone')"
                     v-model="person.phone">
            </div>
            <div class="mb-3">
              <label for="dateCreated" class="form-label"> {{ $t("dateCreated") }} </label>
              <input class="form-control input" id="dateCreated" name="dateCreated" :placeholder="$t('dateCreated')"
                     disabled :value="formatDate(person.dateCreated)">
            </div>
          </div>
          <div class="col">
            <div class="mb-3">
              <label for="status" class="form-label"> {{ $t("status") }} </label>
              <select name="status" id="status" v-model="person.status" class="form-select">
                <option value="ACTIVE" :selected="person.status == 'ACTIVE'">{{ $t("active") }}</option>
                <option value="INACTIVE" :selected="person.status == 'INACTIVE'">{{ $t("inactive") }}</option>
              </select>
            </div>

            <div class="mb-3 row">
              <div class="col-8">
                <label for="city" class="form-label">{{ $t("city") }}</label>
                <input class="form-control" list="datalistOptions" v-model="citySearch" @keydown="getCities"
                       placeholder="Type to search...">
                <datalist id="datalistOptions">
                  <option v-for="city in cities" :value="city.name" :data-value="city.id"/>
                </datalist>
              </div>
              <div class="col-2">
                <label for="state" class="form-label">{{ $t("state") }}</label>
                <input type="text" name="state" class="form-control" disabled v-if="person.city !== undefined"
                       :value="person.city.state.acronym">
              </div>
            </div>
          </div>

        </div>
        <div class="card-footer">
          <button class="btn btn-secondary" @click="$router.back">{{ $t("back") }}</button>
          <button class="btn-primary" type="submit">
            {{ $t("save") }}
          </button>
        </div>
      </form>
    </div>
  </main>
</template>

<style scoped>

</style>