package org.example.service;

import org.example.model.Nurse;

import java.util.List;

public interface NurseService {
    List<Nurse> getAllNurses();
    Nurse getNurseById(long id);
    Nurse addNurse(Nurse nurse);
    Nurse updateNurse(long id, Nurse nurse);
    void deleteNurse(long id);
}
