package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Patient data generating interface.
 * Implemented by BloodPressureDataGenerator, BloodSaturationDataGenerator, HeartRateDataGenerator and EDGDataGenerator.
 */
public interface PatientDataGenerator {

    /**
     * Generates data for a patient.
     * 
     * @param patientId ID of the patient to generate data for.
     * @param outputStrategy Strategy for the generated data to be outputted.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
