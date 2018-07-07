<template>
  <div id="content">
    <h3>Medication Details</h3>
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
      <b-row>
        <b-col>
          <b-btn type="submit" variant="primary" :block="true">Save</b-btn>
        </b-col>
        <b-col>
          <b-btn @click="showModal" variant="danger" :block="true">Delete</b-btn>
        </b-col>
      </b-row>
    </b-form>
    <b-modal ref="deleteModal" @ok="remove">
      Are you sure?
    </b-modal>
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
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/medications",
      this.id,
      res => {
        this.form = res.data;
        this.selected = this.form.type;
      },
      res => {
        res => this.$toastr.e(res.data.error);
      }
    );
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
      crudService.update(
        "/api/medications",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Medication updated!");
          this.$router.push("/medications");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/medications",
        this.id,
        res => {
          this.$toastr.s("Medication deleted!");
          this.$router.push("/medications");
        },
        res => {
          this.$toastr.e(res.data.error);
        }
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
