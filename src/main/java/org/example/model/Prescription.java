package org.example.model;
import lombok.Data;

@Data
public class Prescription {
    private int id;
    private int doctorId;
    private int patientId;
    private int nurseId;
    private int diagnosisId;
    private String description;

    public Prescription() {
    }

    public Prescription(int id, int doctorId, int patientId, int nurseId, int diagnosisId, String description) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.diagnosisId = diagnosisId;
        this.description = description;
    }
}