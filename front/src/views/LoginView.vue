<script lang="ts">
import {useLoginStore} from "@/store/modules/loginModule.js";

export default {
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    async login() {
      const credentials = {
        email: this.email,
        password: this.password
      };

      let response = await useLoginStore().login(credentials);

      if (response != null) {
        localStorage.setItem('authToken', response.token);
        localStorage.setItem('username', response.username);
        this.$router.push('/home');
      } else {
        alert('Login falhou. Verifique suas credenciais.');
      }
    }
  },

};
</script>

<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header bg-secondary text-white">
            <h3 class="mb-0">Login</h3>
          </div>
          <div class="card-body">
            <form @submit.prevent="login">
              <div class="mb-3">
                <label for="username" class="form-label">Email:</label>
                <input type="text" v-model="email" class="form-control" required>
              </div>

              <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" v-model="password" class="form-control" required>
              </div>

              <button type="submit" class="btn btn-primary">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


