package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * This class generates alert data for patients.
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    //Changed to camelCase
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * Constructs a new AlertGenerator with the given number of patients.
     * 
     * @param patientCount Number of patients to generate alert data for.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alert data for a patient and outputs it.
     * 
     * @param patientID ID of patient to generate data for. 
     * @param outputStrategy Strategy for outputting the generated data.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", 
                    //
                    "resolved");
                }
            } else {
                // Changed to camelCase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(
                        // Changed so the column limit is not exceeded
                        patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println(
                // Changed so the column limit is not exceeded
                "An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
