<script lang="ts" setup>
import ComponentHeader from "@/components/ComponentHeader.vue";
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";

import ComponentToastError from "@/components/toasts/ComponentToastError.vue";
import ComponentToastSuccess from "@/components/toasts/ComponentToastSuccess.vue";
</script>
<script lang="ts">
import util from "@/mixins/util";
import { useModulesStore } from "@/store/modules/admin/modulesModule";
import { useProfileStore } from "@/store/modules/admin/profilesModule";
import type LinkSidebar from "@/store/types/linkSidebar";
import type Module from "@/store/types/module";
import type Profile from "@/store/types/profile";

export default {
  components: {ComponentToastError, ComponentToastSuccess, ComponentHeader, ComponentSidebarInner},
  async created() {
    let id: string = this.$route.params.id as string
    if (id != null && id.trim() != "") {
      this.profileOriginal = await useProfileStore().getOne(id)
      this.profile = JSON.parse(JSON.stringify(this.profileOriginal)) as Profile;
      this.text.push("edit")
      this.edit = true
    } else {
      this.profile.dateCreated = new Date()
      this.text.push("add")
    }

    await useModulesStore().get(-1);
    this.modules = useModulesStore().modules
    this.links = useProfileStore().links
  },
  mixins: [util],
  data() {
    return {
      text: ["admin", "profiles"],
      profile: {} as Profile,
      profileOriginal: {} as Profile,
      modules: Array<Module>(),
      edit: false,
      selectedModule: '',
      links: Array<LinkSidebar>()
    }
  },
  methods: {
    async save() {
      if (this.isProfileEqual(this.profile, this.profileOriginal)) {
        this.$router.push({path: '/admin/profiles'})
      } else {
        await useProfileStore().save(this.profile)
      }
    },
    isProfileEqual(profileA: Profile, profileB: Profile) {
      return JSON.stringify(profileA) === JSON.stringify(profileB);
    },
    linkModule() {
      const selectedModule = this.modules.find(module => module.name === this.selectedModule);
      if (selectedModule != undefined) useProfileStore().addModule(this.profileOriginal.id, selectedModule.id)
    }
  }
}
</script>

<template>
  <ComponentHeader inner :text="text"/>
  <ComponentToastSuccess message="success"/>
  <ComponentToastError/>

  <main>
    <div class="col-3">
      <ComponentSidebarInner :links="links" title="profiles"/>
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
                     v-model="profile.name" required>
            </div>
            <div class="mb-3">
              <label for="dateCreated" class="form-label"> {{ $t("dateCreated") }} </label>
              <input class="form-control input" id="dateCreated" name="dateCreated" :placeholder="$t('dateCreated')"
                     disabled :value="$d(profile.dateCreated)">
            </div>
          </div>
          <div class="col p-3 d-flex flex-column" v-if="edit && modules.length > 1">
            <h4>{{ $t("modules") }}</h4>
            <div>
              <input class="form-control" list="datalistOptions" v-model="selectedModule"
                     placeholder="Type to search...">
              <datalist id="datalistOptions">
                <option v-for="module in modules" :key="module.id" :value="module.name" :data-value="module.id"/>
              </datalist>
              <button class="btn-primary mt-2" @click.prevent="linkModule">{{ $t("link") }}</button>
            </div>
            <hr>
            <h4>{{ $t("links") }}</h4>
            <span v-for="module in profile.adminModules" :key="module.id"> {{ module.name }}</span>
          </div>
        </div>
        <div class="card-footer">
          <button class="btn btn-secondary" @click="$router.push('profiles')">{{ $t("back") }}</button>
          <button class="btn-primary" type="submit">
            {{ $t("save") }}
          </button>
        </div>
      </form>
    </div>
  </main>
</template>

<style scoped>
.input {
  display: flex;
  width: 395px;
  padding: 4px 8px;
  align-items: flex-start;
  gap: 8px;
  background: #F9FAFA;
}

.input:focus {
  color: var(--bs-body-color);
  background-color: var(--bs-body-bg);
  border: 1px solid rgba(0, 0, 0, 0.1);
  outline: 0;
  box-shadow: 0 0 0 0.15rem rgba(0, 0, 0, 0.1);
}

.form-label {
  color: #161616;
  line-height: 18px;
  font-weight: 500;
}
</style>