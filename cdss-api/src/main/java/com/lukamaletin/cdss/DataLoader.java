package com.lukamaletin.cdss;

import com.lukamaletin.cdss.model.*;
import com.lukamaletin.cdss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final DiseaseRepository diseaseRepository;

    private final IngredientRepository ingredientRepository;

    private final MedicationRepository medicationRepository;

    private final PatientRepository patientRepository;

    private final SymptomRepository symptomRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(DiseaseRepository diseaseRepository, IngredientRepository ingredientRepository,
                      MedicationRepository medicationRepository, PatientRepository patientRepository,
                      SymptomRepository symptomRepository, UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.diseaseRepository = diseaseRepository;
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
        this.patientRepository = patientRepository;
        this.symptomRepository = symptomRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        final User admin1 = userRepository.save(new User("Admin", "Admin", "admin", passwordEncoder.encode("admin"), Role.ADMIN));
        final User medic1 = userRepository.save(new User("Medic", "Medic", "medic", passwordEncoder.encode("medic"), Role.MEDIC));
        final User medic2 = userRepository.save(new User("Medic", "Medic", "medic2", passwordEncoder.encode("medic2"), Role.MEDIC));
        final User medic3 = userRepository.save(new User("Medic", "Medic", "medic3", passwordEncoder.encode("medic3"), Role.MEDIC));

        final Symptom symptom1 = symptomRepository.save(new Symptom("Runny nose", false));
        final Symptom symptom2 = symptomRepository.save(new Symptom("Sore throat", false));
        final Symptom symptom3 = symptomRepository.save(new Symptom("Headache", false));
        final Symptom symptom4 = symptomRepository.save(new Symptom("Sneezing", false));
        final Symptom symptom5 = symptomRepository.save(new Symptom("Coughing", false));
        final Symptom symptom6 = symptomRepository.save(new Symptom("Increased body temperature (>38°C)", true));
        final Symptom symptom7 = symptomRepository.save(new Symptom("Increased body temperature (>=40°C, <=41°C)", true));
        final Symptom symptom8 = symptomRepository.save(new Symptom("Shivering", false));
        final Symptom symptom9 = symptomRepository.save(new Symptom("Pain in the ears", false));
        final Symptom symptom10 = symptomRepository.save(new Symptom("Appetite loss", false));
        final Symptom symptom11 = symptomRepository.save(new Symptom("Fatigue", false));
        final Symptom symptom12 = symptomRepository.save(new Symptom("Yellow nose secret", false));
        final Symptom symptom13 = symptomRepository.save(new Symptom("Swollen eye", false));
        final Symptom symptom14 = symptomRepository.save(new Symptom("Cold or fever in last 60 days", true));
        final Symptom symptom15 = symptomRepository.save(new Symptom("High blood pressure measured more than 10 times in last 6 months", true));
        final Symptom symptom16 = symptomRepository.save(new Symptom("Frequent urination", false));
        final Symptom symptom17 = symptomRepository.save(new Symptom("Weight loss", false));
        final Symptom symptom18 = symptomRepository.save(new Symptom("Nausea and vomiting", false));
        final Symptom symptom19 = symptomRepository.save(new Symptom("Nocturia", false));
        final Symptom symptom20 = symptomRepository.save(new Symptom("Swollen legs and joints", false));
        final Symptom symptom21 = symptomRepository.save(new Symptom("Choking", false));
        final Symptom symptom22 = symptomRepository.save(new Symptom("Chest pain", false));
        final Symptom symptom23 = symptomRepository.save(new Symptom("Suffering from hypertension more than 6 months", true));
        final Symptom symptom24 = symptomRepository.save(new Symptom("Suffering from diabetes", true));
        final Symptom symptom25 = symptomRepository.save(new Symptom("Recovering from surgery", true));
        final Symptom symptom26 = symptomRepository.save(new Symptom("Diarrhea", false));
        final Symptom symptom27 = symptomRepository.save(new Symptom("Diagnosed with disease with increased body temperature symptom in last 14 days", true));
        final Symptom symptom28 = symptomRepository.save(new Symptom("Antibiotics prescribed in last 21 days", true));

        Disease disease1 = new Disease("Cold", 1);
        final Set<SymptomDisease> symptoms1 = new HashSet<>();
        symptoms1.add(new SymptomDisease(symptom1, disease1, false));
        symptoms1.add(new SymptomDisease(symptom2, disease1, false));
        symptoms1.add(new SymptomDisease(symptom3, disease1, false));
        symptoms1.add(new SymptomDisease(symptom4, disease1, false));
        symptoms1.add(new SymptomDisease(symptom5, disease1, false));
        disease1.setSymptoms(symptoms1);
        disease1 = diseaseRepository.save(disease1);

        Disease disease2 = new Disease("Fever", 1);
        final Set<SymptomDisease> symptoms2 = new HashSet<>();
        symptoms2.add(new SymptomDisease(symptom4, disease2, false));
        symptoms2.add(new SymptomDisease(symptom2, disease2, false));
        symptoms2.add(new SymptomDisease(symptom5, disease2, false));
        symptoms2.add(new SymptomDisease(symptom6, disease2, false));
        symptoms2.add(new SymptomDisease(symptom1, disease2, false));
        symptoms2.add(new SymptomDisease(symptom3, disease2, false));
        symptoms2.add(new SymptomDisease(symptom8, disease2, false));
        disease2.setSymptoms(symptoms2);
        disease2 = diseaseRepository.save(disease2);

        Disease disease3 = new Disease("Tonsillitis", 1);
        final Set<SymptomDisease> symptoms3 = new HashSet<>();
        symptoms3.add(new SymptomDisease(symptom2, disease3, false));
        symptoms3.add(new SymptomDisease(symptom9, disease3, false));
        symptoms3.add(new SymptomDisease(symptom3, disease3, false));
        symptoms3.add(new SymptomDisease(symptom7, disease3, false));
        symptoms3.add(new SymptomDisease(symptom8, disease3, false));
        symptoms3.add(new SymptomDisease(symptom10, disease3, false));
        symptoms3.add(new SymptomDisease(symptom11, disease3, false));
        symptoms3.add(new SymptomDisease(symptom12, disease3, false));
        disease3.setSymptoms(symptoms3);
        disease3 = diseaseRepository.save(disease3);

        Disease disease4 = new Disease("Sinusitis", 1);
        final Set<SymptomDisease> symptoms4 = new HashSet<>();
        symptoms4.add(new SymptomDisease(symptom13, disease4, false));
        symptoms4.add(new SymptomDisease(symptom3, disease4, false));
        symptoms4.add(new SymptomDisease(symptom12, disease4, false));
        symptoms4.add(new SymptomDisease(symptom2, disease4, false));
        symptoms4.add(new SymptomDisease(symptom6, disease4, false));
        symptoms4.add(new SymptomDisease(symptom5, disease4, false));
        symptoms4.add(new SymptomDisease(symptom14, disease4, false));
        disease4.setSymptoms(symptoms4);
        disease4 = diseaseRepository.save(disease4);

        Disease disease5 = new Disease("Hypertension", 2);
        final Set<SymptomDisease> symptoms5 = new HashSet<>();
        symptoms5.add(new SymptomDisease(symptom15, disease5, false));
        disease5.setSymptoms(symptoms5);
        disease5 = diseaseRepository.save(disease5);

        Disease disease6 = new Disease("Diabetes", 2);
        final Set<SymptomDisease> symptoms6 = new HashSet<>();
        symptoms6.add(new SymptomDisease(symptom16, disease6, false));
        symptoms6.add(new SymptomDisease(symptom17, disease6, false));
        symptoms6.add(new SymptomDisease(symptom11, disease6, false));
        symptoms6.add(new SymptomDisease(symptom18, disease6, false));
        disease6.setSymptoms(symptoms6);
        disease6 = diseaseRepository.save(disease6);

        Disease disease7 = new Disease("Chronic kidney disease", 3);
        final Set<SymptomDisease> symptoms7 = new HashSet<>();
        symptoms7.add(new SymptomDisease(symptom11, disease7, false));
        symptoms7.add(new SymptomDisease(symptom19, disease7, false));
        symptoms7.add(new SymptomDisease(symptom20, disease7, false));
        symptoms7.add(new SymptomDisease(symptom21, disease7, false));
        symptoms7.add(new SymptomDisease(symptom22, disease7, false));
        symptoms7.add(new SymptomDisease(symptom23, disease7, true));
        symptoms7.add(new SymptomDisease(symptom24, disease7, true));
        disease7.setSymptoms(symptoms7);
        disease7 = diseaseRepository.save(disease7);

        Disease disease8 = new Disease("Acute kidney injury", 3);
        final Set<SymptomDisease> symptoms8 = new HashSet<>();
        symptoms8.add(new SymptomDisease(symptom25, disease8, true));
        symptoms8.add(new SymptomDisease(symptom11, disease8, false));
        symptoms8.add(new SymptomDisease(symptom21, disease8, false));
        symptoms8.add(new SymptomDisease(symptom20, disease8, false));
        symptoms8.add(new SymptomDisease(symptom26, disease8, false));
        symptoms8.add(new SymptomDisease(symptom27, disease8, true));
        symptoms8.add(new SymptomDisease(symptom28, disease8, true));
        disease8.setSymptoms(symptoms8);
        disease8 = diseaseRepository.save(disease8);

        final Ingredient ingredient1 = ingredientRepository.save(new Ingredient("Ingredient 1"));
        final Ingredient ingredient2 = ingredientRepository.save(new Ingredient("Ingredient 2"));
        final Ingredient ingredient3 = ingredientRepository.save(new Ingredient("Ingredient 3"));
        final Ingredient ingredient4 = ingredientRepository.save(new Ingredient("Ingredient 4"));

        final Set<Ingredient> ingredients1 = new HashSet<>();
        ingredients1.add(ingredient1);
        ingredients1.add(ingredient2);
        final Medication medication1 = medicationRepository.save(new Medication("Antibiotic 1", MedicationType.ANTIBIOTIC, ingredients1));

        final Set<Ingredient> ingredients2 = new HashSet<>();
        ingredients2.add(ingredient3);
        ingredients2.add(ingredient4);
        final Medication medication2 = medicationRepository.save(new Medication("Painkiller 1", MedicationType.PAINKILLER, ingredients2));

        final Set<Ingredient> ingredients3 = new HashSet<>();
        ingredients3.add(ingredient1);
        ingredients3.add(ingredient3);
        final Medication medication3 = medicationRepository.save(new Medication("Other medication 1", MedicationType.OTHER, ingredients3));


        Patient patient1 = new Patient("p123", "Patient1", "Patient1", "123 456", new Date());
        final Set<Ingredient> allergies1 = new HashSet<>();
        allergies1.add(ingredient1);
        patient1.setAllergies(allergies1);
        final Set<Therapy> therapies1 = new HashSet<>();
        // "Cold or fever in last 60 days"
        therapies1.add(new Therapy(new Date(), false, false, false, patient1, null, disease1, null));
        // "High blood pressure measured more than 10 times in last 6 months":
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        therapies1.add(new Therapy(new Date(), false, false, true, patient1, null, null, null));
        // "Suffering from hypertension more than 6 months":
        therapies1.add(new Therapy(createPastDate(190), true, false, true, patient1, null, disease5, null));
        // "Suffering from diabetes":
        therapies1.add(new Therapy(createPastDate(123), true, false, false, patient1, null, disease6, null));
        // "Recovering from surgery":
        therapies1.add(new Therapy(createPastDate(1), false, true, false, patient1, null, null, null));
        // "Diagnosed with disease with increased body temperature symptom in last 14 days":
        therapies1.add(new Therapy(createPastDate(12), false, false, false, patient1, null, disease2, null));
        // "Antibiotics prescribed in last 21 days":
        final Set<Medication> medications1 = new HashSet<>();
        medications1.add(medication1);
        therapies1.add(new Therapy(createPastDate(19), false, false, false, patient1, null, null, medications1));
        // "Get potentially chronically ill patients":
        therapies1.add(new Therapy(createPastDate(2), false, false, false, patient1, null, disease3, null));
        therapies1.add(new Therapy(createPastDate(4), false, false, false, patient1, null, disease3, null));
        therapies1.add(new Therapy(createPastDate(6), false, false, false, patient1, null, disease3, null));
        therapies1.add(new Therapy(createPastDate(8), false, false, false, patient1, null, disease3, null));
        therapies1.add(new Therapy(createPastDate(10), false, false, false, patient1, null, disease3, null));
        therapies1.add(new Therapy(createPastDate(12), false, false, false, patient1, null, disease3, null));
        // "Get potentially addicted patients":
        final Set<Medication> medications2 = new HashSet<>();
        medications2.add(medication2);
        therapies1.add(new Therapy(createPastDate(10), false, false, false, patient1, medic1, null, medications2));
        therapies1.add(new Therapy(createPastDate(20), false, false, false, patient1, medic2, null, medications2));
        therapies1.add(new Therapy(createPastDate(30), false, false, false, patient1, medic3, null, medications2));
        therapies1.add(new Therapy(createPastDate(40), false, false, false, patient1, medic1, null, medications2));
        therapies1.add(new Therapy(createPastDate(50), false, false, false, patient1, medic1, null, medications2));
        therapies1.add(new Therapy(createPastDate(60), false, false, false, patient1, medic1, null, medications2));
        patient1.setTherapies(therapies1);
        patient1 = patientRepository.save(patient1);

        Patient patient2 = new Patient("p456", "Patient2", "Patient2", "456 789", new Date());
        final Set<Therapy> therapies2 = new HashSet<>();
        // "Get weakened immunity patients":
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease1, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        therapies2.add(new Therapy(createPastDate(10), false, false, false, patient2, medic1, disease2, medications1));
        patient2.setTherapies(therapies2);
        patient2 = patientRepository.save(patient2);
    }

    private Date createPastDate(int days) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }
}
