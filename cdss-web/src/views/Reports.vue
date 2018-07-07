<template>
    <div id="content">
        <b-row>
            <b-col id="content-left">
                <h3>Chronically Ill Patients</h3>
                <br/>
                <b-table striped hover :items="patients.chronicallyIllPatients" :fields="fields" @row-clicked="details"></b-table>
            </b-col>
            <b-col id="content-middle">
                <h3>Potentially Addicted Patients</h3>
                <br/>
                <b-table striped hover :items="patients.addictedPatients" :fields="fields" @row-clicked="details"></b-table>
            </b-col>
            <b-col id="content-right">
                <h3>Weakened Immunity Patients</h3>
                <br/>
                <b-table striped hover :items="patients.weakenedImmunityPatients" :fields="fields" @row-clicked="details"></b-table>
            </b-col>
        </b-row>
    </div>
</template>

<script>
import ruleService from "../api/rule-service.js";
export default {
  data() {
    return {
      patients: {
        chronicallyIllPatients: [],
        addictedPatients: [],
        weakenedImmunityPatients: []
      },
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
        }
      ],
      filter: null
    };
  },
  created() {
    ruleService.getReports(
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
#content-left {
  padding-left: 10%;
  padding-right: 5%;
}
#content-middle {
  padding-left: 5%;
  padding-right: 5%;
}
#content-right {
  padding-left: 5%;
  padding-right: 10%;
}
</style>
