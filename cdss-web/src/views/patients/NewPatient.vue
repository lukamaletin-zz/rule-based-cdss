<template>
  <div id="content">
    <h1>New Patient</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Personal ID">
        <b-form-input type="text" v-model="form.personalId" required placeholder="Enter personal ID" autofocus>
        </b-form-input>
      </b-form-group>
      <b-form-group label="First name">
        <b-form-input type="text" v-model="form.firstName" required placeholder="Enter first name">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Last name">
        <b-form-input type="text" v-model="form.lastName" required placeholder="Enter last name">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Phone number">
        <b-form-input type="text" v-model="form.phoneNumber" required placeholder="Enter phone number">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Date of birth">
        <b-form-input type="date" v-model="form.dateOfBirth" required placeholder="Enter date of birth">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Allergies">
        <multiselect v-model="form.allergies" placeholder="Type to search..." label="name" track-by="id" :options="ingredients" :multiple="true"></multiselect>
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
        personalId: "",
        firstName: "",
        lastName: "",
        phoneNumber: "",
        dateOfBirth: new Date().toISOString().substring(0, 10),
        allergies: []
      },
      ingredients: []
    };
  },
  created() {
    crudService.findAll(
      "/api/ingredients",
      res => {
        this.ingredients = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
  },
  methods: {
    submit(e) {
      e.preventDefault();
      crudService.create(
        "/api/patients",
        this.form,
        res => {
          this.$toastr.s("Patient added!");
          this.$router.push("/patients");
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
