<template>
  <div id="content">
    <h3>Medic Details</h3>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="First name">
        <b-form-input type="text" v-model="form.firstName" required placeholder="Enter first name" autofocus>
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
      id: "",
      form: {
        firstName: "",
        lastName: "",
        username: "",
        password: ""
      }
    };
  },
  created() {
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/medics",
      this.id,
      res => {
        this.form = res.data;
      },
      res => {
        res => this.$toastr.e(res.data.error);
      }
    );
  },
  methods: {
    submit(e) {
      e.preventDefault();
      crudService.update(
        "/api/medics",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Medic updated!");
          this.$router.push("/medics");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/medics",
        this.id,
        res => {
          this.$toastr.s("Medic deleted!");
          this.$router.push("/medics");
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
