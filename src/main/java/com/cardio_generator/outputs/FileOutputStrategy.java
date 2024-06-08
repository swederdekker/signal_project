package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

//Class name changed to have capital F
public class FileOutputStrategy implements OutputStrategy { 
    // changed to BaseDirectory camelCase
    private String baseDirectory; 
    // file_map changed to camelcase
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); 

    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            // Whitespace added for closing brackets
            Files.createDirectories(Paths.get(baseDirectory) ); 
        } catch (IOException e) {
            // Whitespace added for closing brackets
            System.err.println("Error creating base directory: " + e.getMessage() ); 
            return;
        }
        // Set the FilePath variable
        // FilePath chagned to camelCase and whitespace added to closing brackets
        // Changed so column limit of 100 isn't exceeding
        String filePath = fileMap.computeIfAbsent(
            label, k -> Paths.get(baseDirectory, label + ".txt").toString() ); 

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(
                // Whitespace added for closing brackets
                // Changed so column limit of 100 isn't exceeding
                Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND) ) ) { 
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", 
            // Changed so column limit of 100 isn't exceeding
            patientId, timestamp, label, data);
        } catch (Exception e) {
            // Whitespace added for closing brackets
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage() ); 
        }
    }
}