package com.cardio_generator.outputs;

/**
 * Interface that defines the output strategy for patient data.
 * Implemented by TcpOutputStrategy, FileOutputStrategy, ConsoleOutputStrategy and WebSocketOutputStrategy.
 */
public interface OutputStrategy {

    /**
     * Outputs patient data according to the parameters.
     * 
     * @param patientId ID of the patient.
     * @param timestamp Time when the data was generated.
     * @param label Label to identify the type of patient data.
     * @param data Data to be outputted.
     */
    void output(int patientId, long timestamp, String label, String data);
}
