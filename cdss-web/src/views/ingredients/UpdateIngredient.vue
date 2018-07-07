<template>
  <div id="content">
    <h3>Ingredient Details</h3>
    <br/>
    <b-form @submit="submit">
      <b-form-group label="Name">
        <b-form-input type="text" v-model="form.name" required placeholder="Enter name" autofocus>
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
      form: {
        name: ""
      }
    };
  },
  created() {
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/ingredients",
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
        "/api/ingredients",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Ingredient updated!");
          this.$router.push("/ingredients");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/ingredients",
        this.id,
        res => {
          this.$toastr.s("Ingredient deleted!");
          this.$router.push("/ingredients");
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
