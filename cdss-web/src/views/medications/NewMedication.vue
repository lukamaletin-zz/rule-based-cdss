<template>
  <div id="content">
    <h1>New Medication</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Name">
        <b-form-input type="text" v-model="form.name" required placeholder="Enter name">
        </b-form-input>
      </b-form-group>
      <b-form-group>
        <b-form-radio-group v-model="selected" :options="options">
        </b-form-radio-group>
      </b-form-group>
      <b-form-group label="Ingredients">
        <multiselect v-model="form.ingredients" placeholder="Type to search..." label="name" track-by="id" :options="ingredients" :multiple="true"></multiselect>
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
        name: "",
        type: "",
        ingredients: []
      },
      ingredients: [],
      options: ["ANTIBIOTIC", "PAINKILLER", "OTHER"],
      selected: ""
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
      this.form.type = this.selected;
      crudService.create(
        "/api/medications",
        this.form,
        res => {
          this.$toastr.s("Medication added!");
          this.$router.push("/medications");
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
