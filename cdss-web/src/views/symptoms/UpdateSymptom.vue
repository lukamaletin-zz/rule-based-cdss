<template>
  <div id="content">
    <h3>Symptom Details</h3>
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
        id: "",
        name: "",
        complex: false
      },
      options: ["Complex"],
      selected: []
    };
  },
  created() {
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/symptoms",
      this.id,
      res => {
        this.form = res.data;
        if (res.data.complex) {
          this.selected = [this.options[0]];
        }
      },
      res => {
        res => this.$toastr.e(res.data.error);
      }
    );
  },
  methods: {
    submit(e) {
      e.preventDefault();
      this.form.complex = this.selected.includes("Complex");
      crudService.update(
        "/api/symptoms",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Symptom updated!");
          this.$router.push("/symptoms");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/symptoms",
        this.id,
        res => {
          this.$toastr.s("Symptom deleted!");
          this.$router.push("/symptoms");
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
