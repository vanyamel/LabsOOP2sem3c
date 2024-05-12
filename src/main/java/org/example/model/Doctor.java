package org.example.model;

import lombok.Data;

@Data
public class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor() {}

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

}