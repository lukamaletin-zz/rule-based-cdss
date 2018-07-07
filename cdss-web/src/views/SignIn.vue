<template>
  <div id="content">
    <h1>Sign In</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Username">
        <b-form-input type="text" v-model="form.username" required placeholder="Enter username" autofocus>
        </b-form-input>
      </b-form-group>
      <b-form-group label="Password">
        <b-form-input type="password" v-model="form.password" required placeholder="Enter password">
        </b-form-input>
      </b-form-group>
      <b-btn type="submit" variant="primary" :block="true">Submit</b-btn>
    </b-form>
  </div>
</template>

<script>
import authService from "../api/auth-service.js";
export default {
  data() {
    return {
      form: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    submit(e) {
      e.preventDefault();
      authService.signin(
        this.form,
        res => {
          const authenticatedUser = res.data;
          localStorage.setItem(
            "authenticatedUser",
            JSON.stringify(authenticatedUser)
          );
          this.$store.commit("setRole", authenticatedUser.role);

          if (this.$store.getters.isMedic) {
            this.$store.commit("connectSocket", message => {
              this.$toastr.w(message);
            });
            this.$router.push("/");
          } else {
            this.$router.push("/medics");
          }

          this.$toastr.s(`Welcome ${this.form.username}`);
        },
        res => this.$toastr.e(res.data.error)
      );
    }
  }
};
</script>

<style scoped>
#content {
  padding-left: 40%;
  padding-right: 40%;
}
</style>
