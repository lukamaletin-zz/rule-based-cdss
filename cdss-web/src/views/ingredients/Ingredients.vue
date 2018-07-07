<template>
  <div id="content">
    <h1>Ingredients</h1>
    <br/>
    <b-row>
      <b-col md="6">
        <b-btn variant="primary" to="/new-ingredient">Add new ingredient</b-btn>
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
    <b-table striped hover :items="ingredients" :fields="fields" :filter="filter" @row-clicked="details">
    </b-table>
  </div>
</template>

<script>
import crudService from "../../api/crud-service.js";
export default {
  data() {
    return {
      ingredients: [],
      fields: [
        {
          key: "name",
          sortable: true
        }
      ],
      filter: null
    };
  },
  created() {
    crudService.findAll(
      "/api/ingredients",
      res => {
        this.ingredients = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
  },
  methods: {
    details(row, index) {
      this.$router.push(`/ingredients/${row.id}`);
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
