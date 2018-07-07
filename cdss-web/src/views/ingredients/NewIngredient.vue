<template>
  <div id="content">
    <h1>New Ingredient</h1>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Name">
        <b-form-input type="text" v-model="form.name" required placeholder="Enter name" autofocus>
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
        name: ""
      }
    };
  },
  methods: {
    submit(e) {
      e.preventDefault();
      crudService.create(
        "/api/ingredients",
        this.form,
        res => {
          this.$toastr.s("Ingredient added!");
          this.$router.push("/ingredients");
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
