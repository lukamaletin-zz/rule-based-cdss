import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import SignIn from './views/SignIn.vue';
import Patients from './views/patients/Patients.vue';
import NewPatient from './views/patients/NewPatient.vue';
import UpdatePatient from './views/patients/UpdatePatient.vue';
import Reports from './views/Reports.vue';
import Medics from './views/medics/Medics.vue';
import NewMedic from './views/medics/NewMedic.vue';
import UpdateMedic from './views/medics/UpdateMedic.vue';
import Diseases from './views/diseases/Diseases.vue';
import NewDisease from './views/diseases/NewDisease.vue';
import UpdateDisease from './views/diseases/UpdateDisease.vue';
import Symptoms from './views/symptoms/Symptoms.vue';
import NewSymptom from './views/symptoms/NewSymptom.vue';
import UpdateSymptom from './views/symptoms/UpdateSymptom.vue';
import Medications from './views/medications/Medications.vue';
import NewMedication from './views/medications/NewMedication.vue';
import UpdateMedication from './views/medications/UpdateMedication.vue';
import Ingredients from './views/ingredients/Ingredients.vue';
import NewIngredient from './views/ingredients/NewIngredient.vue';
import UpdateIngredient from './views/ingredients/UpdateIngredient.vue';

Vue.use(Router)

const getRole = () => {
    const authenticatedUser = localStorage.getItem("authenticatedUser");
    if (authenticatedUser) {
        return JSON.parse(authenticatedUser).role;
    }
    return '';
}

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'MEDIC');
            }
        },
        {
            path: '/signin',
            name: 'signin',
            component: SignIn
        },
        {
            path: '/patients',
            name: 'patients',
            component: Patients,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'MEDIC');
            }
        },
        {
            path: '/new-patient',
            name: 'new-patient',
            component: NewPatient,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'MEDIC');
            }
        },
        {
            path: '/patients/:id',
            name: 'patient-details',
            component: UpdatePatient,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'MEDIC');
            }
        },
        {
            path: '/reports',
            name: 'reports',
            component: Reports,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'MEDIC');
            }
        },
        {
            path: '/medics',
            name: 'medics',
            component: Medics,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/new-medic',
            name: 'new-medic',
            component: NewMedic,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/medics/:id',
            name: 'medic-details',
            component: UpdateMedic,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/diseases',
            name: 'diseases',
            component: Diseases,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/new-disease',
            name: 'new-disease',
            component: NewDisease,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/diseases/:id',
            name: 'disease-details',
            component: UpdateDisease,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/symptoms',
            name: 'symptoms',
            component: Symptoms,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/new-symptom',
            name: 'new-symptom',
            component: NewSymptom,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/symptoms/:id',
            name: 'symptom-details',
            component: UpdateSymptom,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/medications',
            name: 'medications',
            component: Medications,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/new-medication',
            name: 'new-medication',
            component: NewMedication,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/medications/:id',
            name: 'medication-details',
            component: UpdateMedication,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/ingredients',
            name: 'ingredients',
            component: Ingredients,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/new-ingredient',
            name: 'new-ingredient',
            component: NewIngredient,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        },
        {
            path: '/ingredients/:id',
            name: 'ingredient-details',
            component: UpdateIngredient,
            beforeEnter: (to, from, next) => {
                next(getRole() == 'ADMIN');
            }
        }
    ]
})
