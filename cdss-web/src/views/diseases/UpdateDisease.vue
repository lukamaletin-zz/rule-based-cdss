<template>
  <div id="content">
    <h3>Disease Details</h3>
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
        groupNumber: 0,
        specificSymptoms: [],
        generalSymptoms: []
      },
      symptoms: []
    };
  },
  created() {
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/diseases",
      this.id,
      res => {
        this.form.name = res.data.name;
        this.form.groupNumber = res.data.groupNumber;
        this.form.specificSymptoms = res.data.symptoms.filter(s => s.specific);
        this.form.generalSymptoms = res.data.symptoms.filter(s => !s.specific);
      },
      res => {
        res => this.$toastr.e(res.data.error);
      }
    );
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
      crudService.update(
        "/api/diseases",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Disease updated!");
          this.$router.push("/diseases");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/diseases",
        this.id,
        res => {
          this.$toastr.s("Disease deleted!");
          this.$router.push("/diseases");
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
