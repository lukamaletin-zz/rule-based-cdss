<template>
  <div id="content">
    <h1>New Symptom</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Name">
        <b-form-input type="text" v-model="form.name" required placeholder="Enter name" autofocus>
        </b-form-input>
      </b-form-group>
      <b-form-group>
        <b-form-checkbox-group v-model="selected" :options="options">
        </b-form-checkbox-group>
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
        complex: false
      },
      options: [{ text: "Complex", value: "complex" }],
      selected: []
    };
  },
  methods: {
    submit(e) {
      e.preventDefault();
      this.form.complex = this.selected.includes("complex");
      crudService.create(
        "/api/symptoms",
        this.form,
        res => {
          this.$toastr.s("Symptom added!");
          this.$router.push("/symptoms");
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
