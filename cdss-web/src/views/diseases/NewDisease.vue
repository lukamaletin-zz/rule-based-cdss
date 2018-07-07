<template>
  <div id="content">
    <h1>New Disease</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Name">
        <b-form-input type="text" v-model="form.name" required placeholder="Enter name">
        </b-form-input>
      </b-form-group>
      <b-form-group label="Group number">
        <b-form-input type="number" v-model="form.groupNumber" min="0" required>
        </b-form-input>
      </b-form-group>
      <b-form-group label="Specific symptoms">
        <multiselect v-model="form.specificSymptoms" placeholder="Type to search..." label="name" track-by="id" :options="symptoms" :multiple="true"></multiselect>
      </b-form-group>
      <b-form-group label="General symptoms">
        <multiselect v-model="form.generalSymptoms" placeholder="Type to search..." label="name" track-by="id" :options="symptoms" :multiple="true"></multiselect>
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
        groupNumber: 0,
        specificSymptoms: [],
        generalSymptoms: []
      },
      symptoms: []
    };
  },
  created() {
    crudService.findAll(
      "/api/symptoms",
      res => {
        this.symptoms = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
  },
  methods: {
    submit(e) {
      e.preventDefault();
      crudService.create(
        "/api/diseases",
        this.form,
        res => {
          this.$toastr.s("Disease added!");
          this.$router.push("/diseases");
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
