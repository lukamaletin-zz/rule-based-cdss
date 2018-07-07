<template>
  <div id="content">
    <b-row>
      <b-col id="content-left">
        <h3>Patient Details</h3>
        <br/>
        <b-form @submit="submit">
          <b-form-group label="Personal ID">
            <b-form-input type="text" v-model="form.personalId" required placeholder="Enter personal ID" disabled>
            </b-form-input>
          </b-form-group>
          <b-form-group label="First name">
            <b-form-input type="text" v-model="form.firstName" required placeholder="Enter first name" autofocus>
            </b-form-input>
          </b-form-group>
          <b-form-group label="Last name">
            <b-form-input type="text" v-model="form.lastName" required placeholder="Enter last name">
            </b-form-input>
          </b-form-group>
          <b-form-group label="Phone number">
            <b-form-input type="text" v-model="form.phoneNumber" required placeholder="Enter phone number">
            </b-form-input>
          </b-form-group>
          <b-form-group label="Date of birth">
            <b-form-input type="date" v-model="form.dateOfBirth" required placeholder="Enter date of birth">
            </b-form-input>
          </b-form-group>
          <b-form-group label="Allergies">
            <multiselect v-model="form.allergies" placeholder="Type to search..." label="name" track-by="id" :options="ingredients" :multiple="true"></multiselect>
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
      </b-col>
      <b-col id="content-right">
        <h3>Therapies</h3>
        <br/>
        <b-row>
          <b-col>
            <b-pagination :total-rows="form.therapies.length" :per-page="perPage" v-model="currentPage" class="my-0" />
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
        <b-table striped hover :items="form.therapies" :fields="fields" :filter="filter" @filtered="onFiltered" :current-page="currentPage" :per-page="perPage">
        </b-table>
      </b-col>
    </b-row>
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
        personalId: "",
        firstName: "",
        lastName: "",
        phoneNumber: "",
        dateOfBirth: "",
        allergies: [],
        therapies: []
      },
      ingredients: [],
      fields: [
        {
          key: "prescriptionDate",
          sortable: true,
          formatter: value => {
            return new Date(value).toISOString().substring(0, 10);
          }
        },
        {
          key: "prescribedBy",
          sortable: true
        },
        {
          key: "disease",
          sortable: true,
          formatter: value => {
            return value ? value.name : "";
          }
        },
        {
          key: "chronic",
          sortable: true
        },
        {
          key: "surgeryPerformed",
          sortable: true
        }
      ],
      filter: null,
      currentPage: 1,
      perPage: 9
    };
  },
  created() {
    this.id = this.$route.params["id"];
    crudService.findById(
      "/api/patients",
      this.id,
      res => {
        this.form = res.data;
        this.form.dateOfBirth = this.form.dateOfBirth.substring(0, 10);
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
      crudService.update(
        "/api/patients",
        this.id,
        this.form,
        res => {
          this.$toastr.s("Patient updated!");
          this.$router.push("/patients");
        },
        res => this.$toastr.e(res.data.error)
      );
    },
    showModal() {
      this.$refs.deleteModal.show();
    },
    remove() {
      crudService.delete(
        "/api/patients",
        this.id,
        res => {
          this.$toastr.s("Patient deleted!");
          this.$router.push("/patients");
        },
        res => {
          this.$toastr.e(res.data.error);
        }
      );
    },
    onFiltered(filteredItems) {
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    }
  }
};
</script>

<style scoped>
#content {
  padding-left: 10%;
  padding-right: 10%;
}
#content-left {
  padding-right: 5%;
}
#content-right {
  padding-left: 5%;
}
</style>
