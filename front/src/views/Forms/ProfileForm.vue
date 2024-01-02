<script lang="ts">
import ComponentSidebarInner from "@/components/sidebar/ComponentSidebarInner.vue";
import ComponentHeader from "@/components/ComponentHeader.vue";
import {useProfileStore} from "@/store/modules/profilesModule";
import util from "@/mixins/util";
import {useModulesStore} from "@/store/modules/modulesModule";
import type Profile from "@/store/types/profile";
import type Module from "@/store/types/module";
import type LinkSidebar from "@/store/types/linkSidebar";

export default {
  components: {ComponentHeader, ComponentSidebarInner},
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

    await useModulesStore().get(0);
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
      useProfileStore().addModule(this.profileOriginal.id, this.selectedModule)
    }
  }
}
</script>

<template>
  <div>
    <ComponentHeader inner :text="text"/>
    <main>
      <div class="col-3">
        <ComponentSidebarInner :links="links" title="profiles"/>
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
                       v-model="profile.name">
              </div>
              <div class="mb-3">
                <label for="dateCreated" class="form-label"> {{ $t("dateCreated") }} </label>
                <input class="form-control input" id="dateCreated" name="dateCreated" :placeholder="$t('dateCreated')"
                       disabled :value="formatDate(profile.dateCreated)">
              </div>
            </div>
            <div class="col p-3 d-flex flex-column" v-if="edit && modules.length > 1">
              <h4>{{ $t("modules") }}</h4>
              <div>
                <select name="module" id="module" class="form-select" v-model="selectedModule">
                  <option v-for="m in modules" :value="m.id">
                    {{ m.name }}
                  </option>
                </select>
                <button class="btn-primary mt-2" @click.prevent="linkModule">{{ $t("link") }}</button>
              </div>
              <hr>
              <h4>{{ $t("links") }}</h4>
              <span v-for="m in profile.adminModules"> {{ m.name }}</span>
            </div>
          </form>
        </div>
        <div class="card-footer">
          <button class="btn-primary" @click="save">
            {{ $t("save") }}
          </button>
        </div>
      </div>
    </main>
  </div>
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