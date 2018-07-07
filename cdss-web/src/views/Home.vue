<template>
  <div id="content">
    <b-row>
      <b-col id="content-left">
        <div>
          <h3>Diagnosis Support</h3>
          <b-form @submit="getDiagnosis">
            <b-form-group label="Patient">
              <multiselect v-model="diagnosisForm.patient" placeholder="Select patient..." :custom-label="selectPatientLabel" track-by="id" :options="patients"></multiselect>
            </b-form-group>
            <b-form-group label="Symptoms">
              <multiselect v-model="diagnosisForm.symptoms" placeholder="Select symptoms..." label="name" track-by="id" :options="simpleSymptoms" :multiple="true"></multiselect>
            </b-form-group>
            <b-form-group label="Body temperature (°C)">
              <b-form-input type="number" v-model="diagnosisForm.bodyTemperature" min="0" required>
              </b-form-input>
            </b-form-group>
            <b-form-group>
              <b-row>
                <b-col>
                  <b-btn :disabled="!diagnosisForm.patient" type="submit" variant="primary" :block="true">Submit</b-btn>
                </b-col>
                <b-col>
                  <b-btn @click="resetDiagnosisForm" variant="secondary" :block="true">Reset</b-btn>
                </b-col>
              </b-row>
            </b-form-group>
          </b-form>
        </div>
        <div>
          <h3>Related Diseases</h3>
          <b-form @submit="getRelatedDiseases">
            <b-form-group label="Symptoms">
              <multiselect v-model="symptomsForm.symptoms" placeholder="Select symptoms..." label="name" track-by="id" :options="symptoms" :multiple="true"></multiselect>
            </b-form-group>
            <b-form-group>
              <b-row>
                <b-col>
                  <b-btn :disabled="!symptomsForm.symptoms || symptomsForm.symptoms.length == 0" type="submit" variant="primary" :block="true">Submit</b-btn>
                </b-col>
                <b-col>
                  <b-btn @click="resetSymptomsForm" variant="secondary" :block="true">Reset</b-btn>
                </b-col>
              </b-row>
            </b-form-group>
          </b-form>
        </div>
        <div>
          <h3>Disease Symptoms</h3>
          <b-form @submit="getDiseaseSymptoms">
            <b-form-group label="Disease">
              <multiselect v-model="diseaseForm.disease" placeholder="Select disease..." label="name" track-by="id" :options="diseases"></multiselect>
            </b-form-group>
            <b-form-group>
              <b-row>
                <b-col>
                  <b-btn :disabled="!diseaseForm.disease" type="submit" variant="primary" :block="true">Submit</b-btn>
                </b-col>
                <b-col>
                  <b-btn @click="resetDiseaseForm" variant="secondary" :block="true">Reset</b-btn>
                </b-col>
              </b-row>
            </b-form-group>
          </b-form>
        </div>
      </b-col>
      <b-col id="content-right">
        <div>
          <h3>Prescribe Diagnosis</h3>
          <b-form @submit="submitTherapy">
            <b-form-group label="Disease">
              <multiselect v-model="therapyForm.disease" placeholder="Select disease..." label="name" track-by="id" :options="diseases"></multiselect>
            </b-form-group>
            <b-form-group label="Additional info">
              <b-form-checkbox-group stacked v-model="therapyForm.selected" :options="therapyForm.options">
              </b-form-checkbox-group>
            </b-form-group>
            <b-form-group label="Medications">
              <multiselect v-model="therapyForm.medications" placeholder="Select medications..." :custom-label="selectMedicationLabel" label="name" track-by="id" :options="medications" :multiple="true"></multiselect>
            </b-form-group>
            <b-form-group>
              <b-row>
                <b-col>
                  <b-btn :disabled="!diagnosisForm.patient || !therapyForm.disease" variant="primary" @click="validateTherapy" :block="true">Validate</b-btn>
                </b-col>
                <b-col>
                </b-col>
              </b-row>
              <br/>
              <b-row>
                <b-col>
                  <b-btn :disabled="!diagnosisForm.patient || !therapyForm.disease" type="submit" variant="primary" :block="true">Submit</b-btn>
                </b-col>
                <b-col>
                  <b-btn @click="resetTherapyForm" variant="secondary" :block="true">Reset</b-btn>
                </b-col>
              </b-row>
            </b-form-group>
          </b-form>
        </div>
      </b-col>
    </b-row>
    <b-modal ref="infoModal" ok-only>
      <span v-html="modalMessage"></span>
    </b-modal>
  </div>
</template>

<script>
import crudService from "../api/crud-service.js";
import ruleService from "../api/rule-service.js";
export default {
  data() {
    return {
      diagnosisForm: {
        patient: null,
        bodyTemperature: 37,
        symptoms: []
      },
      symptomsForm: {
        symptoms: []
      },
      diseaseForm: {
        disease: ""
      },
      therapyForm: {
        disease: "",
        selected: [],
        options: [
          { text: "Chronic", value: "chronic" },
          { text: "Surgery", value: "surgery" },
          { text: "High blood pressure", value: "bloodPressure" }
        ],
        medications: []
      },
      patients: [],
      symptoms: [],
      simpleSymptoms: [],
      diseases: [],
      medications: [],
      modalMessage: ""
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
    crudService.findAll(
      "/api/symptoms",
      res => {
        this.symptoms = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
    crudService.findAll(
      "/api/symptoms/simple",
      res => {
        this.simpleSymptoms = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
    crudService.findAll(
      "/api/diseases",
      res => {
        this.diseases = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
    crudService.findAll(
      "/api/medications",
      res => {
        this.medications = res.data;
      },
      res => this.$toastr.e(res.data.error)
    );
  },
  methods: {
    selectPatientLabel(patient) {
      return `${patient.firstName} ${patient.lastName} (${patient.personalId})`;
    },
    selectMedicationLabel(medication) {
      return `${medication.name} (${medication.ingredients
        .map(i => i.name)
        .join("</br>")})`;
    },
    getDiagnosis(e) {
      e.preventDefault();
      ruleService.getDiagnosis(
        this.diagnosisForm,
        res => {
          const data = res.data;
          this.toast(`${data.disease.name} (${data.percentageMatched})`);
        },
        res => {
          this.$toastr.e(res.data.error);
        }
      );
    },
    resetDiagnosisForm() {
      this.diagnosisForm.patient = null;
      this.diagnosisForm.bodyTemperature = 37;
      this.diagnosisForm.symptoms = [];
    },
    getRelatedDiseases(e) {
      e.preventDefault();
      ruleService.getRelatedDiseases(
        this.symptomsForm.symptoms,
        res => {
          this.toast(res.data.map(d => d.disease.name).join("</br>"));
        },
        res => {
          this.$toastr.e(res.data.error);
        }
      );
    },
    resetSymptomsForm() {
      this.symptomsForm.symptoms = [];
    },
    getDiseaseSymptoms(e) {
      e.preventDefault();
      ruleService.getDiseaseSymptoms(this.diseaseForm.disease.name, res => {
        this.toast(res.data.map(s => s.name).join("</br>"));
      });
    },
    resetDiseaseForm() {
      this.diseaseForm.disease = "";
    },
    createTherapyRequest() {
      return {
        prescriptionDate: new Date(),
        prescribedBy: JSON.parse(localStorage.getItem("authenticatedUser")),
        chronic: this.therapyForm.selected.includes("chronic"),
        surgeryPerformed: this.therapyForm.selected.includes("surgery"),
        highBloodPressure: this.therapyForm.selected.includes("bloodPressure"),
        patient: this.diagnosisForm.patient,
        disease: this.therapyForm.disease,
        medications: this.therapyForm.medications
      };
    },
    validateTherapy() {
      const therapy = this.createTherapyRequest();
      ruleService.validateTherapy(
        therapy,
        res => {
          if (res.data && res.data.length > 0) {
            this.toast(
              `Patient is allergic to: ${res.data
                .map(i => i.name)
                .join("</br>")}`
            );
          } else {
            this.toast("No allergies!");
          }
        },
        res => {
          this.$toastr.e(res.data.error);
        }
      );
    },
    submitTherapy(e) {
      e.preventDefault();
      const therapy = this.createTherapyRequest();
      crudService.create(
        "/api/therapies",
        therapy,
        res => {
          this.$toastr.s("Therapy created!");
        },
        res => {
          this.$toastr.e(res.data.error);
        }
      );
    },
    resetTherapyForm() {
      this.therapyForm.disease = "";
      this.therapyForm.selected = [];
      this.therapyForm.medications = [];
    },
    toast(message) {
      this.modalMessage = message;
      this.$refs.infoModal.show();
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
