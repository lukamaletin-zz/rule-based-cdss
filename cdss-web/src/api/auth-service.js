import Vue from 'vue';
import VueResource from 'vue-resource';

Vue.use(VueResource);

const baseUrl = '/api/auth';

const authService = {
    signin: (data, success, error) => {
        return Vue.http.post(`${baseUrl}/signin`, data).then(success, error);
    },

    signout: (success, error) => {
        return Vue.http.post(`${baseUrl}/signout`).then(success, error);
    }
}

export default authService;
