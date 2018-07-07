<template>
  <div>
    <div>
      <b-navbar toggleable="md" type="dark" variant="dark" fixed="top">
        <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
        <b-navbar-brand to="/">CDSS</b-navbar-brand>
        <b-collapse is-nav id="nav_collapse">
          <b-navbar-nav>
            <b-nav-item v-if="isAdmin()" to="/medics">Medics</b-nav-item>
            <b-nav-item v-if="isAdmin()" to="/diseases">Diseases</b-nav-item>
            <b-nav-item v-if="isAdmin()" to="/symptoms">Symptoms</b-nav-item>
            <b-nav-item v-if="isAdmin()" to="/medications">Medications</b-nav-item>
            <b-nav-item v-if="isAdmin()" to="/ingredients">Ingredients</b-nav-item>
            <b-nav-item v-if="isMedic()" to="/">Diagnosis</b-nav-item>
            <b-nav-item v-if="isMedic()" to="/patients">Patients</b-nav-item>
            <b-nav-item v-if="isMedic()" to="/reports">Reports</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item v-if="isAuthenticated()" v-on:click="signout()">Sign Out</b-nav-item>
            <b-nav-item v-else to="/signin">Sign In</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <div id="content">
      <b-container fluid>
        <router-view/>
      </b-container>
    </div>
  </div>
</template>

<script>
import authService from "./api/auth-service.js";
export default {
  methods: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    isAdmin() {
      return this.$store.getters.isAdmin;
    },
    isMedic() {
      return this.$store.getters.isMedic;
    },
    signout() {
      const cb = () => {
        this.$store.commit("setRole", "");
        this.$store.commit("disconnectSocket");
        localStorage.clear();
        this.$router.push("/signin");
      };
      authService.signout(cb, cb);
    }
  },
  created() {
    const authenticatedUser = localStorage.getItem("authenticatedUser");
    if (authenticatedUser) {
      this.$store.commit("setRole", JSON.parse(authenticatedUser).role);
      if (this.$store.getters.isMedic) {
        this.$store.commit("connectSocket", message => {
          this.$toastr.w(message);
        });
      }
    } else {
      this.$router.push("/signin");
    }
  }
};
</script>

<style>
#content {
  margin-top: 70px;
}
</style>
