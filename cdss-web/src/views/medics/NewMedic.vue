<template>
  <div id="content">
    <h1>New Medic</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="First name">
        <b-form-input type="text" v-model="form.firstName" required placeholder="Enter first name">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Last name">
        <b-form-input type="text" v-model="form.lastName" required placeholder="Enter last name">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Username">
        <b-form-input type="text" v-model="form.username" required placeholder="Enter username">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Password">
        <b-form-input type="password" v-model="form.password" required placeholder="Enter password">
        </b-form-input>
      </b-form-group>
      <b-btn type="submit" variant="primary" :block="true">Save</b-btn>
    </b-form>
  </div>
</template>

<script>
import crudService from "../../api/crud-service.js";
export default {
  data() {
    return {
      form: {
        firstName: "",
        lastName: "",
        username: "",
        password: ""
      }
    };
  },
  methods: {
    submit(e) {
      e.preventDefault();
      crudService.create(
        "/api/medics",
        this.form,
        res => {
          this.$toastr.s("Medic added!");
          this.$router.push("/medics");
        },
        res => this.$toastr.e(res.data.error)
      );
    }
  }
};
</script>

<style scoped>
#content {
  padding-left: 30%;
  padding-right: 30%;
}
</style>
