<script lang="ts">
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import {useProfileStore} from "@/store/modules/profilesModule";
import util from "@/mixins/util";
import type Profile from "@/store/types/profile";
import type LinkSidebar from "@/store/types/linkSidebar";
import {useUserStore} from "@/store/modules/userModule";
import type User from "@/store/types/user";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
import ComponentToastError from "@/components/toasts/ComponentToastError.vue";

export default {
  components: {ComponentToastError, ComponentToastSuccess, ComponentHeader, ComponentSidebarInner},
  async created() {
    let id: string = this.$route.params.id as string
    if (id != null && id.trim() != "") {
      this.userOriginal = await useUserStore().getOne(id)
      this.user = JSON.parse(JSON.stringify(this.userOriginal)) as User;
      this.text.push("edit")
      this.edit = true
    } else {
      this.user.dateCreated = new Date()
      this.text.push("add")
    }

    await useProfileStore().get(0);
    this.profiles = useProfileStore().profiles
    this.links = useUserStore().links
  },
  mixins: [util],
  data() {
    return {
      text: ["admin", "users"],
      profiles: Array<Profile>(),
      userOriginal: {} as User,
      user: {} as User,
      edit: false,
      links: Array<LinkSidebar>()
    }
  },
  methods: {
    async save() {
      if (this.isEqual(this.user, this.userOriginal)) {
        this.$router.push({path: '/admin/users'})
      } else {
        await useUserStore().save(this.user)
      }
    },
    isEqual(userA: User, userB: User) {
      return JSON.stringify(userA) === JSON.stringify(userB);
    },
  }
}
</script>

<template>
  <ComponentToastSuccess message="saveSuccess"/>
  <ComponentToastError/>
  <div>
    <ComponentHeader inner :text="text"/>
    <main>
      <div class="col-3">
        <ComponentSidebarInner :links="links" title="users"/>
      </div>
      <div class="col-8 card ">
        <div class="card-header">
          <h2 class="text-center py-2">
            {{ $t(text[text.length - 1]) }}
          </h2>
        </div>
        <div class="card-body">
          <form class="d-flex">
            <div class="col">
              <div class="mb-3">
                <label for="name" class="form-label"> {{ $t("name") }} </label>
                <input class="form-control input" id="name" name="name" :placeholder="$t('name')"
                       v-model="user.name">
              </div>
              <div class="mb-3">
                <label for="name" class="form-label"> {{ $t("email") }} </label>
                <input class="form-control input" name="email" :placeholder="$t('email')"
                       v-model="user.email">
              </div>
              <div class="mb-3">
                <label for="name" class="form-label"> {{ $t("cpf") }} </label>
                <input class="form-control input" name="email" :placeholder="$t('cpf')"
                       v-model="user.cpf">
              </div>
              <div class="mb-3">
                <label for="name" class="form-label"> {{ $t("phone") }} </label>
                <input class="form-control input" name="phone" :placeholder="$t('phone')"
                       v-model="user.phone">
              </div>
              <div class="mb-3">
                <label for="dateCreated" class="form-label"> {{ $t("dateCreated") }} </label>
                <input class="form-control input" id="dateCreated" name="dateCreated" :placeholder="$t('dateCreated')"
                       disabled :value="formatDate(user.dateCreated)">
              </div>
            </div>
            <div class="col">
              <label for="profile" class="form-label"> {{ $t("profile") }} </label>
              <select name="profile" id="profile" v-model="user.profile" class="form-select">
                <option v-for="profile in profiles" :value="profile">{{profile.name}}</option>
              </select>
              <label for="language" class="form-label"> {{ $t("language") }} </label>

              <select name="language" id="language" v-model="user.language" class="form-select">
                <option value="EN" :selected="user.language == 'EN'">{{$t("english")}}</option>
                <option value="PT">{{$t("portuguese")}}</option>
              </select>
              <label for="status" class="form-label"> {{ $t("status") }} </label>

              <select name="status" id="status" v-model="user.status" class="form-select">
                <option value="ACTIVE" :selected="user.status == 'ACTIVE'">{{$t("active")}}</option>
                <option value="INACTIVE" :selected="user.status == 'INACTIVE'">{{$t("inactive")}}</option>
              </select>
              <hr>
              <div class="mb-3">
                <label for="password" class="form-label"> {{ $t("password") }} </label>
                <input class="form-control input" name="password" :placeholder="$t('password')"
                       type="password" v-model="user.password" :disabled="edit">
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" :checked="user.admin" v-model="user.admin">
                <label class="form-check-label" for="flexCheckDefault">
                  {{$t("admin")}}
                </label>
              </div>

            </div>
          </form>
        </div>
        <div class="card-footer">
          <button class="btn btn-secondary" @click="$router.back">{{ $t("back") }}</button>
          <button class="btn-primary" @click="save">
            {{ $t("save") }}
          </button>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>

</style>