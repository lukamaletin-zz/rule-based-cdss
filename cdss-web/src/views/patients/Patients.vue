<template>
  <div id="content">
    <h1>Patients</h1>
    <br/>
    <b-row>
      <b-col md="6">
        <b-btn variant="primary" to="/new-patient">Add new patient</b-btn>
      </b-col>
      <b-col>
        <b-form-group horizontal>
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Type to search..." autofocus/>
            <b-input-group-append>
              <b-btn :disabled="!filter" @click="filter = ''">Clear</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
    </b-row>
    <b-table striped hover :items="patients" :fields="fields" :filter="filter" @row-clicked="details">
    </b-table>
  </div>
</template>

<script>
import crudService from "../../api/crud-service.js";
export default {
  data() {
    return {
      patients: [],
      fields: [
        {
          key: "personalId",
          sortable: true
        },
        {
          key: "firstName",
          sortable: true
        },
        {
          key: "lastName",
          sortable: true
        },
        {
          key: "phoneNumber",
          sortable: true
        },
        {
          key: "dateOfBirth",
          sortable: true,
          formatter: value => {
            return new Date(value).toISOString().substring(0, 10);
          }
        }
      ],
      filter: null
    };
  },
  created() {
    crudService.findAll(
      "/api/patients",
      res => {
        this.patients = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
  },
  methods: {
    details(row, index) {
      this.$router.push(`/patients/${row.id}`);
    }
  }
};
</script>

<style scoped>
#content {
  padding-left: 20%;
  padding-right: 20%;
}
</style>
