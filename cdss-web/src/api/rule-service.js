import Vue from 'vue';
import VueResource from 'vue-resource';

Vue.use(VueResource);

const baseUrl = '/api/reasoning';

const ruleService = {
    getDiagnosis: (data, success, error) => {
        return Vue.http.post(`${baseUrl}/diagnose`, data).then(success, error);
    },

    getRelatedDiseases: (data, success, error) => {
        return Vue.http.post(`${baseUrl}/related`, data).then(success, error);
    },

    getDiseaseSymptoms: (data, success, error) => {
        return Vue.http.post(`${baseUrl}/symptoms`, data).then(success, error);
    },

    validateTherapy: (data, success, error) => {
        return Vue.http.post(`${baseUrl}/validate`, data).then(success, error);
    },

    getReports: (success, error) => {
        return Vue.http.get(`${baseUrl}/reports`).then(success, error);
    },
}

export default ruleService;
